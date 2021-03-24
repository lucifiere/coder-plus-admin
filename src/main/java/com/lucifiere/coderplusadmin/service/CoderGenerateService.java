package com.lucifiere.coderplusadmin.service;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.Preconditions;
import com.lucifiere.coderplus.bootstrap.Bootstrap;
import com.lucifiere.coderplus.bootstrap.IdlBasedCodeGenerator;
import com.lucifiere.coderplus.bootstrap.SqlBasedCodeGenerator;
import com.lucifiere.coderplus.render.View;
import com.lucifiere.coderplus.render.views.SourceCodeView;
import com.lucifiere.coderplus.templates.embed.EmbedTemplates;
import com.lucifiere.coderplusadmin.domain.CodeGenerateRequest;
import com.lucifiere.coderplusadmin.domain.CodeGenerateResponse;
import com.lucifiere.coderplusadmin.module.CodeGenerateNodeSetting;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author created by XD.Wang
 * Date 2021/3/24.
 */
@Service
public class CoderGenerateService {

    public Bootstrap createBootstrap(CodeGenerateRequest setting) {
        Preconditions.checkArgument(StrUtil.isNotBlank(setting.getAuthor()), "请输入代码作者~");
        Preconditions.checkArgument(StrUtil.isNotBlank(setting.getDriveCode()), "请输入驱动语句~");
        Bootstrap bootstrap = null;
        if (Objects.equals(setting.getNodeType(), CodeGenerateNodeSetting.DDL.name())) {
            bootstrap = new SqlBasedCodeGenerator().setDriveCode(setting.getDriveCode()).setOutputDir("output").setPkg(setting.getPkg()).setWorkspacePath(setting.getWorkspacePath()).setAuthor(setting.getAuthor());
        }
        if (Objects.equals(setting.getNodeType(), CodeGenerateNodeSetting.IDL.name())) {
            bootstrap = new IdlBasedCodeGenerator().setDriveCode(setting.getDriveCode()).setOutputDir("output").setPkg(setting.getPkg()).setWorkspacePath(setting.getWorkspacePath()).setAuthor(setting.getAuthor());
        }
        Preconditions.checkArgument(bootstrap != null, "执行失败！");
        return bootstrap;
    }

    public CodeGenerateResponse createCodeGenerateResponse(List<View> views) {
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
