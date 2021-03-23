package com.lucifiere.coderplus.render.views;

import com.lucifiere.coderplus.common.FileSetting;
import com.lucifiere.coderplus.render.Render;
import com.lucifiere.coderplus.render.View;

public class SourceCodeView implements View {

    private String templateId;

    private String content;

    private FileSetting fileSetting;

    private String name;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileSetting getFileSetting() {
        return fileSetting;
    }

    public void setFileSetting(FileSetting fileSetting) {
        this.fileSetting = fileSetting;
    }
}
