package com.lucifiere.coderplusadmin.controller;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.lucifiere.coderplus.bootstrap.Bootstrap;
import com.lucifiere.coderplus.bootstrap.IdlBasedCodeGenerator;
import com.lucifiere.coderplus.bootstrap.SqlBasedCodeGenerator;
import com.lucifiere.coderplus.render.View;
import com.lucifiere.coderplus.render.views.SourceCodeView;
import com.lucifiere.coderplus.templates.embed.EmbedTemplates;
import com.lucifiere.coderplusadmin.domain.CodeGenerateRequest;
import com.lucifiere.coderplusadmin.domain.CodeGenerateResponse;
import com.lucifiere.coderplusadmin.module.CodeGenerateNodeSetting;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

/**
 * 控制台
 *
 * @author created by XD.Wang
 * Date 2021/2/4.
 */
@RestController
@RequestMapping("dashboard")
public class DashboardController {

    @GetMapping("sql-ddl")
    public ModelAndView sqlDdlDrive() {
        ModelAndView m = new ModelAndView("dashboard");
        m.addObject("nodes", CodeGenerateNodeSetting.toMap());
        m.addObject("node", CodeGenerateNodeSetting.toMap(CodeGenerateNodeSetting.DDL));
        return m;
    }

    @GetMapping("thrift-idl")
    public ModelAndView thriftIdlDrive() {
        ModelAndView m = new ModelAndView("dashboard");
        m.addObject("nodes", CodeGenerateNodeSetting.toMap());
        m.addObject("node", CodeGenerateNodeSetting.toMap(CodeGenerateNodeSetting.IDL));
        return m;
    }

    @PostMapping("generate")
    public CodeGenerateResponse generateCode(@RequestBody CodeGenerateRequest request) {
        try {
            Preconditions.checkArgument(StrUtil.isNotBlank(request.getAuthor()), "请输入代码作者~");
            Preconditions.checkArgument(StrUtil.isNotBlank(request.getDriveCode()), "请输入驱动语句~");
            Bootstrap bootstrap = null;
            if (Objects.equals(request.getNodeType(), CodeGenerateNodeSetting.DDL.name())) {
                bootstrap = new SqlBasedCodeGenerator().setDriveCode(request.getDriveCode()).setPkg(request.getPkg()).setAuthor(request.getAuthor());
            }
            if (Objects.equals(request.getNodeType(), CodeGenerateNodeSetting.IDL.name())) {
                bootstrap = new IdlBasedCodeGenerator().setDriveCode(request.getDriveCode()).setPkg(request.getPkg()).setAuthor(request.getAuthor());
                ;
            }
            Preconditions.checkArgument(bootstrap != null, "执行失败！");
            List<View> views = bootstrap.renderingViews(Lists.newArrayList(EmbedTemplates.BIZ_POJO,
                    EmbedTemplates.MODEL,
                    EmbedTemplates.EXAMPLE,
                    EmbedTemplates.SER_REPOSITORY,
                    EmbedTemplates.SER_IMPL_REPOSITORY,
                    EmbedTemplates.MYBATIS_XML_MAPPER,
                    EmbedTemplates.MYBATIS_MAPPER));
            return createCodeGenerateResponse(views);
        } catch (Exception e) {
            CodeGenerateResponse r = new CodeGenerateResponse();
            r.setMsg("执行失败！" + e.getMessage());
            r.setSuc(false);
            return r;
        }
    }

    private CodeGenerateResponse createCodeGenerateResponse(List<View> views) {
        CodeGenerateResponse response = new CodeGenerateResponse();
        response.setSuc(true);
        views.stream().filter(v -> v instanceof SourceCodeView).map(v -> (SourceCodeView) v).forEach(view -> {
            switch (view.getTemplateId()) {
                case EmbedTemplates.MODEL:
                    response.setModel(view.getContent());
                    break;
                case EmbedTemplates.EXAMPLE:
                    response.setExample(view.getContent());
                    break;
                case EmbedTemplates.SER_REPOSITORY:
                    response.setRepository(view.getContent());
                    break;
                case EmbedTemplates.SER_IMPL_REPOSITORY:
                    response.setRepositoryImpl(view.getContent());
                    break;
                case EmbedTemplates.MYBATIS_XML_MAPPER:
                    response.setMapper(view.getContent());
                    break;
                case EmbedTemplates.MYBATIS_MAPPER:
                    response.setXml(view.getContent());
                    break;
                default:
            }
        });
        return response;
    }

}