package com.lucifiere.coderplus.templates.embed;

import com.lucifiere.coderplus.common.FileSetting;
import com.lucifiere.coderplus.templates.spec.Template;
import com.lucifiere.coderplus.templates.spec.TemplateSpec;
import com.lucifiere.coderplus.templates.spec.Templates;

/**
 * @author created by XD.Wang
 * Date 2020/9/4.
 */
@Templates
public class BaseServiceTemplatesConfig {

    private static final String SERVICE_TPL_PATH = "/ftl/service.ftl";

    private static final String SERVICE_IMPL_TPL_PATH = "/ftl/service-impl.ftl";

    private static final String MYBATIS_MAPPER_TPL_PATH = "/ftl/mapper.ftl";

    private static final String MYBATIS_MAPPER_XML_TPL_PATH = "/ftl/mapper-xml.ftl";

    private static final String THRIFT_DELEGATE_TPL_PATH = "/ftl/delegate.ftl";

    @Template(EmbedTemplates.SERVICE)
    public TemplateSpec serviceTemplate() {
        TemplateSpec spec = new TemplateSpec();
        spec.setDescription("SERVICE");
        spec.setPath(SERVICE_TPL_PATH);
        spec.addAttr("serviceType", "Service");
        spec.addAttr("serviceOutSuf", "BO");
        spec.addAttr("serviceInSuf", "Query");
        spec.setFileSetting(FileSetting.ofJavaFile());
        return spec;
    }

    @Template(EmbedTemplates.SER_REPOSITORY)
    public TemplateSpec repositoryTemplate() {
        TemplateSpec spec = new TemplateSpec();
        spec.setDescription("SER_REPOSITORY");
        spec.setPath(SERVICE_TPL_PATH);
        spec.addAttr("serviceType", "Repository");
        spec.addAttr("serviceOutSuf", "");
        spec.addAttr("serviceInSuf", "Query");
        spec.setFileSetting(FileSetting.ofJavaFile());
        return spec;
    }

    @Template(EmbedTemplates.SER_MANAGER)
    public TemplateSpec managerTemplate() {
        TemplateSpec spec = new TemplateSpec();
        spec.setDescription("SER_MANAGER");
        spec.setPath(SERVICE_TPL_PATH);
        spec.addAttr("serviceType", "Manager");
        spec.addAttr("serviceOutSuf", "DTO");
        spec.addAttr("serviceInSuf", "Query");
        spec.setFileSetting(FileSetting.ofJavaFile());
        return spec;
    }

    @Template(EmbedTemplates.SERVICE_IMPL)
    public TemplateSpec serviceImplTemplate() {
        TemplateSpec spec = new TemplateSpec();
        spec.setDescription("SERVICE_IMPL");
        spec.setPath(SERVICE_IMPL_TPL_PATH);
        spec.addAttr("subServiceType", "Repository");
        spec.addAttr("serviceType", "Service");
        spec.addAttr("serviceOutSuf", "BO");
        spec.addAttr("serviceInSuf", "Query");
        spec.setFileSetting(FileSetting.ofJavaFile());
        return spec;
    }

    @Template(EmbedTemplates.SER_IMPL_REPOSITORY)
    public TemplateSpec repositoryImplTemplate() {
        TemplateSpec spec = new TemplateSpec();
        spec.setDescription("SER_IMPL_REPOSITORY");
        spec.setPath(SERVICE_IMPL_TPL_PATH);
        spec.addAttr("subServiceType", "Mapper");
        spec.addAttr("serviceType", "Repository");
        spec.addAttr("serviceOutSuf", "");
        spec.addAttr("serviceInSuf", "Query");
        spec.setFileSetting(FileSetting.ofJavaFile());
        return spec;
    }

    @Template(EmbedTemplates.SER_IMPL_MANAGER)
    public TemplateSpec managerImplTemplate() {
        TemplateSpec spec = new TemplateSpec();
        spec.setDescription("SER_IMPL_MANAGER");
        spec.setPath(SERVICE_IMPL_TPL_PATH);
        spec.addAttr("serviceType", "Manager");
        spec.addAttr("subServiceType", "Service");
        spec.addAttr("serviceOutSuf", "DTO");
        spec.addAttr("serviceInSuf", "Req");
        spec.setFileSetting(FileSetting.ofJavaFile());
        return spec;
    }

    @Template(EmbedTemplates.SER_IMPL_THRIFT)
    public TemplateSpec apiImplTemplate() {
        TemplateSpec spec = new TemplateSpec();
        spec.setDescription("SER_IMPL_THRIFT");
        spec.setPath(SERVICE_IMPL_TPL_PATH);
        spec.addAttr("serviceType", "ThriftApi");
        spec.addAttr("subServiceType", "Manager");
        spec.addAttr("serviceOutSuf", "");
        spec.addAttr("serviceInSuf", "");
        spec.setFileSetting(FileSetting.ofJavaFile());
        return spec;
    }

    @Template(EmbedTemplates.MYBATIS_MAPPER)
    public TemplateSpec mapperTemplate() {
        TemplateSpec spec = new TemplateSpec();
        spec.setDescription("MYBATIS_MAPPER");
        spec.setPath(MYBATIS_MAPPER_TPL_PATH);
        spec.addAttr("serviceOutSuf", "");
        spec.addAttr("serviceInSuf", "");
        spec.setFileSetting(FileSetting.ofJavaFile());
        return spec;
    }

    @Template(EmbedTemplates.MYBATIS_XML_MAPPER)
    public TemplateSpec mapperXmlTemplate() {
        TemplateSpec spec = new TemplateSpec();
        spec.setDescription("MYBATIS_XML_MAPPER");
        spec.setPath(MYBATIS_MAPPER_XML_TPL_PATH);
        spec.setFileSetting(FileSetting.ofMyBatisXmlFile("/", "", "Mapper"));
        return spec;
    }

    @Template(EmbedTemplates.THRIFT_DELEGATE)
    public TemplateSpec thriftDelegateTemplate() {
        TemplateSpec spec = new TemplateSpec();
        spec.setDescription("THRIFT_DELEGATE_MAPPER");
        spec.setPath(THRIFT_DELEGATE_TPL_PATH);
        spec.setFileSetting(FileSetting.ofJavaFile());
        return spec;
    }

}
