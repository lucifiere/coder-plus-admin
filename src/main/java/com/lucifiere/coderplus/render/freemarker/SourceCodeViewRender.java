package com.lucifiere.coderplus.render.freemarker;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.Preconditions;
import com.lucifiere.coderplus.model.Model;
import com.lucifiere.coderplus.model.TableModel;
import com.lucifiere.coderplus.render.View;
import com.lucifiere.coderplus.render.views.SourceCodeView;

public class SourceCodeViewRender extends FreemarkerRender {

    public SourceCodeViewRender(String templateId) {
        super(templateId);
    }

    @Override
    protected void processModelBeforeRender(Model model) {
        super.processModelBeforeRender(model);
    }

    @Override
    protected View createView(String tid, String content, Model model) {
        Preconditions.checkArgument(StrUtil.isNotBlank(content));
        Preconditions.checkArgument(model instanceof TableModel);
        SourceCodeView sourceCodeView = new SourceCodeView();
        sourceCodeView.setContent(content);
        sourceCodeView.setTemplateId(tid);
        sourceCodeView.setFileSetting(templateInstant.getTemplateSpec().getFileSetting());
        sourceCodeView.setName(model.getKeyword());
        return sourceCodeView;
    }

}
