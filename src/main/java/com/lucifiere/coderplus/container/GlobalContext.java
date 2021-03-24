package com.lucifiere.coderplus.container;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.StaticLog;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.lucifiere.coderplus.common.ClassManager;
import com.lucifiere.coderplus.common.GlobalConfig;
import com.lucifiere.coderplus.group.spec.Group;
import com.lucifiere.coderplus.group.spec.GroupSpec;
import com.lucifiere.coderplus.group.spec.Groups;
import com.lucifiere.coderplus.templates.spec.Template;
import com.lucifiere.coderplus.templates.spec.TemplateSpec;
import com.lucifiere.coderplus.templates.spec.Templates;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 模板容器
 *
 * @author wuhuilin
 * Date 2020-8-23.
 */
public class GlobalContext {

    private GlobalConfig config;

    private final AtomicBoolean isInit = new AtomicBoolean(false);

    private final Map<String, TemplateSpec> templateMap = Maps.newConcurrentMap();

    private final Map<String, ManagedBeanSpec> componentMap = Maps.newConcurrentMap();

    private final Map<String, GroupSpec> groupMap = Maps.newConcurrentMap();

    public GlobalConfig getConfig() {
        return this.config;
    }

    @SuppressWarnings("unchecked")
    public <T> T getComponent(Class<T> clazz) {
        return (T) componentMap.values().stream().filter(c -> Objects.equals(clazz, c.getClazz())).map(ManagedBeanSpec::getInstant).findAny().orElseThrow(() -> new ContainerException("manager bean not found!！"));
    }

    public <T, R> R calByComponent(Class<T> clazz, Function<T, R> function) {
        T bean = Objects.requireNonNull(getComponent(clazz));
        return function.apply(bean);
    }

    public <T> void doWithComponent(Class<T> clazz, Consumer<T> function) {
        T bean = Objects.requireNonNull(getComponent(clazz));
        function.accept(bean);
    }

    @SuppressWarnings("unchecked")
    public <T> T getComponent(String id) {
        return (T) componentMap.values().stream().filter(c -> Objects.equals(id, c.getId())).findAny().orElseThrow(() -> new ContainerException("manager bean not found!！"));
    }

    public TemplateSpec getTemplateById(String id) {
        return templateMap.get(id);
    }

    public GroupSpec getGroupById(String id) {
        return groupMap.get(id);
    }

    public List<String> getTemplateIdsByGroupId(String groupId) {
        GroupSpec groupSpec = getGroupById(groupId);
        if (null == groupSpec || CollectionUtil.isEmpty(groupSpec.getTemplateIds())) {
            return Lists.newArrayList();
        }
        return groupSpec.getTemplateIds();
    }

    public Set<TemplateSpec> getAllTemplates() {
        return Sets.newHashSet(templateMap.values());
    }

    public static GlobalContext create(GlobalConfig config) {
        GlobalContext container = new GlobalContext();
        if (container.isInit.get()) {
            return container;
        }
        container.init0(config);
        return container;
    }

    private synchronized void init0(GlobalConfig config) {
        try {
            this.config = config;
            registerComponents();
            registerTemplates();
            registerGroups();
            processGlobalContextAware();
            this.isInit.compareAndSet(false, true);
        } catch (Exception e) {
            clear();
            StaticLog.error("global context init failed！", e);
            throw new ContainerException("global context init failed！", e);
        }
    }

    private void processGlobalContextAware() {
        componentMap.values().stream().map(ManagedBeanSpec::getInstant).forEach(bean -> {
            if (bean instanceof GlobalContextAware) {
                GlobalContextAware globalContextAware = (GlobalContextAware) bean;
                ReflectUtil.invoke(globalContextAware, "setGlobalContext", this);
            }
        });
    }

    private void registerTemplates(List<TemplateSpec> templates) {
        Optional.ofNullable(templates).ifPresent(templateSpecs -> templateSpecs.forEach(this::registerTemplate));
    }

    private void registerTemplate(TemplateSpec template) {
        if (null != templateMap.get(template.getId())) {
            return;
        }
        templateMap.put(template.getId(), template);
    }

    private static final String TEMPLATES_EMBED = "com.lucifiere.coderplus.templates.embed";

    private void registerTemplates() {
        String customizedTplPath = config.getTemplatesConfigScanPath();
        Set<Class<?>> templates = ClassManager.getClazzByPath(customizedTplPath, TEMPLATES_EMBED);
        templates.parallelStream().forEach(clazz -> {
            Templates ts = AnnotationUtil.getAnnotation(clazz, Templates.class);
            if (ts != null && !ts.skip()) {
                Object ins = ReflectUtil.newInstance(clazz);
                Arrays.stream(clazz.getDeclaredMethods()).forEach(method -> {
                    Template define = AnnotationUtil.getAnnotation(method, Template.class);
                    if (define != null) {
                        Object obj = ReflectUtil.invoke(ins, method);
                        if (obj instanceof TemplateSpec) {
                            TemplateSpec spec = (TemplateSpec) obj;
                            spec.setId(define.value());
                            templateMap.put(spec.getId(), spec);
                        }
                    }
                });
            }
        });
    }

    private static final String GROUPS_EMBED = "com.dlin.group.embed";

    private void registerGroups() {
        String customizedTplPath = config.getGroupsConfigScanPath();
        Set<Class<?>> groups = ClassManager.getClazzByPath(customizedTplPath, GROUPS_EMBED);
        groups.parallelStream().forEach(clazz -> {
            Groups gs = AnnotationUtil.getAnnotation(clazz, Groups.class);
            if (gs != null) {
                Object instance = ReflectUtil.newInstance(clazz);
                Arrays.stream(clazz.getDeclaredMethods()).forEach(method -> {
                    Group g = AnnotationUtil.getAnnotation(method, Group.class);
                    if (null != g) {
                        Object obj = ReflectUtil.invoke(instance, method);
                        if (obj instanceof GroupSpec) {
                            GroupSpec groupSpec = (GroupSpec) obj;
                            groupSpec.setId(g.value());
                            groupMap.put(groupSpec.getId(), groupSpec);
                        }
                    }
                });
            }
        });
    }

    private void registerComponents() {
        Set<Class<?>> clazzSet = ClassManager.getCoderPlusClazz();
        clazzSet.forEach(clazz -> {
            ManagedBean an = AnnotationUtil.getAnnotation(clazz, ManagedBean.class);
            if (an != null) {
                ManagedBeanSpec c = ManagedBeanSpec.of(clazz);
                Optional.of(an.value()).filter(StrUtil::isNotBlank).ifPresent(c::setId);
                componentMap.put(c.getId(), c);
            }
        });
    }

    private void clear() {
        templateMap.clear();
        groupMap.clear();
        componentMap.clear();
    }

}
