package com.lucifiere.coderplus.render.executor;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.base.Preconditions;
import com.lucifiere.coderplus.container.GlobalContext;
import com.lucifiere.coderplus.container.GlobalContextAware;
import com.lucifiere.coderplus.container.ManagedBean;
import com.lucifiere.coderplus.render.freemarker.SourceCodeViewRender;
import com.lucifiere.coderplus.templates.spec.TemplateSpec;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 责任链管理
 *
 * @author XD.Wang
 * Date 2020/7/25.
 */
@ManagedBean
public class CodeRendersChainManager implements GlobalContextAware {

    private static GlobalContext globalContext;

    @Override
    public void setGlobalContext(GlobalContext context) {
        globalContext = context;
    }

    public static CodeRendersChainManager getManager() {
        return globalContext.getComponent(CodeRendersChainManager.class);
    }

    private RenderWrapper chainingNode(List<RenderWrapper> handlers) {
        for (int i = 0; i < handlers.size() - 1; i++) {
            handlers.get(i).nextIs(handlers.get(i + 1));
        }
        return handlers.get(0);
    }

    public RenderWrapper chaining(List<String> templateIds) {
        Preconditions.checkArgument(templateIds != null && templateIds.size() > 0, "chose a template!");
        Set<TemplateSpec> templateSpecs = globalContext.getAllTemplates();
        Preconditions.checkArgument(CollectionUtil.isNotEmpty(templateSpecs), "no registered templates found！");
        String missingTemplateIds = templateIds.stream()
                .filter(tId -> !templateSpecs.stream().map(TemplateSpec::getId).collect(Collectors.toSet()).contains(tId))
                .collect(Collectors.joining(","));
        Preconditions.checkArgument(StrUtil.isBlank(missingTemplateIds), "template -> " + missingTemplateIds + " not found！");
        return chainingNode(templateIds.stream().map(SourceCodeViewRender::new).map(DefaultRenderWrapper::new).collect(Collectors.toList()));
    }

}
