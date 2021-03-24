package com.lucifiere.coderplusadmin.controller;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.lucifiere.coderplus.bootstrap.Bootstrap;
import com.lucifiere.coderplus.io.FileAccessor;
import com.lucifiere.coderplus.templates.embed.EmbedTemplates;
import com.lucifiere.coderplusadmin.domain.CodeGenerateRequest;
import com.lucifiere.coderplusadmin.domain.CodeGenerateResponse;
import com.lucifiere.coderplusadmin.module.CodeGenerateNodeSetting;
import com.lucifiere.coderplusadmin.service.CoderGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制台
 *
 * @author created by XD.Wang
 * Date 2021/2/4.
 */
@Controller
@RequestMapping("dashboard")
public class DashboardController {

    private final CoderGenerateService coderGenerateService;

    @Autowired
    public DashboardController(CoderGenerateService coderGenerateService) {
        this.coderGenerateService = coderGenerateService;
    }

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

    @PostMapping("download")
    public Object downloadFile(@RequestBody CodeGenerateRequest setting, HttpServletRequest request) {
        try {
            Preconditions.checkArgument(StrUtil.isNotBlank(setting.getWorkspacePath()), "请输入代码文件路径~");
            Bootstrap bootstrap = coderGenerateService.createBootstrap(setting);
            bootstrap.execute(Lists.newArrayList(EmbedTemplates.BIZ_POJO,
                    EmbedTemplates.MODEL,
                    EmbedTemplates.EXAMPLE,
                    EmbedTemplates.SER_REPOSITORY,
                    EmbedTemplates.SER_IMPL_REPOSITORY,
                    EmbedTemplates.MYBATIS_XML_MAPPER,
                    EmbedTemplates.MYBATIS_MAPPER));
            Resource resource = FileAccessor.loadFileResource(setting.getWorkspacePath() + "/" + "output.zip");
            String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            contentType = "application/octet-stream";
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            CodeGenerateResponse r = new CodeGenerateResponse();
            r.setMsg("执行失败！" + e.getMessage());
            r.setSuc(false);
            return r;
        }
    }

}
