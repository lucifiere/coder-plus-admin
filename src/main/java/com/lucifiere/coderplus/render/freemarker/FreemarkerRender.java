package com.lucifiere.coderplus.render.freemarker;

import cn.hutool.log.StaticLog;
import com.google.common.collect.Maps;
import com.lucifiere.coderplus.model.Model;
import com.lucifiere.coderplus.render.AbstractRender;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public abstract class FreemarkerRender extends AbstractRender {

    public FreemarkerRender(String templateId) {
        super(templateId);
    }

    @Override
    protected String doRender(Model model) {
        Writer out = new StringWriter();
        try {
            String templateId = templateInstant.getTemplateSpec().getId();
            FreemarkerTemplateManager manager = FreemarkerTemplateManager.getManager();
            Template template = manager.getTemplate(templateId);
            Map<String, Object> dataModel = Maps.newHashMap();
            dataModel.putAll(model.toAttrMap());
            dataModel.putAll(templateInstant.getTemplateSpec().getAttrs());
            template.process(dataModel, out);
            return out.toString();
        } catch (TemplateException e) {
            StaticLog.error("freemarker template render failed！" + e.getMessage(), e);
            return null;
        } catch (IOException e) {
            StaticLog.error("io exception occurred！" + e.getMessage(), e);
            return null;
        } catch (Exception e) {
            StaticLog.error("render with fatal error！" + e.getMessage(), e);
            return null;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                StaticLog.error("io exception occurred！" + e.getMessage(), e);
            }
        }
    }

}
