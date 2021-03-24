package com.lucifiere.coderplus.exporter;

import com.lucifiere.coderplus.container.GlobalContext;
import com.lucifiere.coderplus.container.GlobalContextAware;
import com.lucifiere.coderplus.container.ManagedBean;
import com.lucifiere.coderplus.render.View;

import java.util.List;
import java.util.Optional;

/**
 * @author created by XD.Wang
 * Date 2021/3/23.
 */
public abstract class AbstractExporter implements Exporter, GlobalContextAware {

    protected GlobalContext context;

    protected List<View> views;

    @Override
    public List<View> getViews() {
        return views;
    }

    @Override
    public String getOutputPath() {
        return context.getConfig().getWorkspacePath() + "/" + Optional.ofNullable(context.getConfig().getOutputDir()).orElse("");
    }

    @Override
    public void setGlobalContext(GlobalContext globalContext) {
        this.context = globalContext;
    }

}
