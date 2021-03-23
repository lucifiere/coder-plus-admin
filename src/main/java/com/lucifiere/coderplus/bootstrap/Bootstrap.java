package com.lucifiere.coderplus.bootstrap;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.log.StaticLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.lucifiere.coderplus.common.GlobalConfig;
import com.lucifiere.coderplus.container.GlobalContext;
import com.lucifiere.coderplus.exporter.Exporter;
import com.lucifiere.coderplus.extract.Extractor;
import com.lucifiere.coderplus.model.Model;
import com.lucifiere.coderplus.render.View;
import com.lucifiere.coderplus.render.executor.*;

import java.util.List;

/**
 * 启动器
 * 根据外部配置串联框架中的各组件，并定义了内容生成的主干逻辑
 *
 * @author XD.Wang
 * Date 2020-8-2.
 */
public abstract class Bootstrap {

    /**
     * 基于模板来生成内容
     *
     * @param templateIds 模板ID
     */
    public void execute(String... templateIds) {
        execute(Lists.newArrayList(templateIds));
    }

    /**
     * 基于模板来生成内容
     *
     * @param templateIds 模板ID
     */
    public void execute(List<String> templateIds) {
        try {
            GlobalContext context = createGlobalContext(templateIds);
            GlobalConfig config = context.getConfig();
            List<View> views = renderingViews(templateIds, context);
            context.doWithComponent(config.getExporter(), exporter -> exporter.export(views));
            StaticLog.info("generate content success! check your file at " + context.calByComponent(config.getExporter(), Exporter::getOutputPath));
        } catch (Exception e) {
            StaticLog.error(e, "generate content failed! ");
        }
    }

    public GlobalContext createGlobalContext(List<String> templateIds) {
        StaticLog.info("start to generate content, templateId -> {}", templateIds);
        Preconditions.checkArgument(CollectionUtil.isNotEmpty(templateIds), "registered template id required！");
        GlobalConfig config = configureContext();
        contextCheckBeforeExecute(config);
        return GlobalContext.create(config);
    }

    public List<View> renderingViews(List<String> templateIds, GlobalContext context) {
        if (context == null) {
            context = createGlobalContext(templateIds);
        }
        GlobalConfig config = context.getConfig();
        Model model = context.calByComponent(config.getExtractor(), Extractor::extract);
        return renderViews(model, templateIds);
    }

    public List<View> renderingViews(List<String> templateIds) {
        return renderingViews(templateIds, null);
    }

    /**
     * 利用执行器去按序渲染模板 1 -> N
     *
     * @param model 数据
     * @return 模型
     */
    private List<View> renderViews(Model model, List<String> templateIds) {
        RenderWrapper renderHeader = CodeRendersChainManager.getManager().chaining(templateIds);
        ConfigurableRendersExecutor rendersExecutor = new ConfigurableRendersExecutor(renderHeader);
        HandlerRequest req = new HandlerRequest();
        req.setModel(model);
        HandlerResponse resp = new HandlerResponse();
        rendersExecutor.execute(req, resp);
        return resp.getViews();
    }

    private void contextCheckBeforeExecute(GlobalConfig config) {
        Preconditions.checkNotNull(config, "global config cant be null！");
        Preconditions.checkNotNull(config.getExporter(), "require a exporter！");
        Preconditions.checkNotNull(config.getExtractor(), "require a extractor！");
        Preconditions.checkNotNull(config.getResolver(), "require a resolver！");
    }

    /**
     * 获取上下文
     *
     * @return 获取上下文
     */
    protected abstract GlobalConfig configureContext();

}
