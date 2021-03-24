package com.lucifiere.coderplusadmin.controller;

import com.google.common.collect.Lists;
import com.lucifiere.coderplus.bootstrap.Bootstrap;
import com.lucifiere.coderplus.render.View;
import com.lucifiere.coderplus.templates.embed.EmbedTemplates;
import com.lucifiere.coderplusadmin.domain.CodeGenerateRequest;
import com.lucifiere.coderplusadmin.domain.CodeGenerateResponse;
import com.lucifiere.coderplusadmin.service.CoderGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 控制台
 *
 * @author created by XD.Wang
 * Date 2021/2/4.
 */
@RestController
@RequestMapping("dashboard")
public class DashboardRestController {

    private final CoderGenerateService coderGenerateService;

    @Autowired
    public DashboardRestController(CoderGenerateService coderGenerateService) {
        this.coderGenerateService = coderGenerateService;
    }

    @PostMapping("generate")
    public CodeGenerateResponse generateCode(@RequestBody CodeGenerateRequest setting) {
        try {
            Bootstrap bootstrap = coderGenerateService.createBootstrap(setting);
            List<View> views = bootstrap.renderingViews(Lists.newArrayList(EmbedTemplates.BIZ_POJO,
                    EmbedTemplates.MODEL,
                    EmbedTemplates.EXAMPLE,
                    EmbedTemplates.SER_REPOSITORY,
                    EmbedTemplates.SER_IMPL_REPOSITORY,
                    EmbedTemplates.MYBATIS_XML_MAPPER,
                    EmbedTemplates.MYBATIS_MAPPER));
            return coderGenerateService.createCodeGenerateResponse(views);
        } catch (Exception e) {
            CodeGenerateResponse r = new CodeGenerateResponse();
            r.setMsg("执行失败！" + e.getMessage());
            r.setSuc(false);
            return r;
        }
    }

}
