package com.lucifiere.coderplus.bootstrap;

import com.lucifiere.coderplus.common.GlobalConfig;
import com.lucifiere.coderplus.exporter.CodeExporter;
import com.lucifiere.coderplus.extract.table.SourceCodeExtractor;
import com.lucifiere.coderplus.resovler.antlr.AntlrThriftResolver;

/**
 * 预制好组件实现类型的启动器
 *
 * @author XD.Wang
 */
public class IdlBasedCodeGenerator extends Bootstrap {

    protected final GlobalConfig config = new GlobalConfig();

    public IdlBasedCodeGenerator setWorkspacePath(String workspacePath) {
        config.setWorkspacePath(workspacePath);
        return this;
    }

    public IdlBasedCodeGenerator setOutputDir(String outputPath) {
        config.setOutputDir(outputPath);
        return this;
    }


    public IdlBasedCodeGenerator setAuthor(String author) {
        config.setAuthor(author);
        return this;
    }

    public IdlBasedCodeGenerator setPkg(String pkg) {
        config.setBasePkg(pkg);
        return this;
    }

    public IdlBasedCodeGenerator setDriveCode(String code) {
        config.setDriveCode(code);
        return this;
    }

    public IdlBasedCodeGenerator setIdlName(String idlName) {
        config.setInputFileName(idlName);
        return this;
    }

    public IdlBasedCodeGenerator setTemplatesPath(String templatesPath) {
        config.setTemplatesConfigScanPath(templatesPath);
        return this;
    }

    public IdlBasedCodeGenerator setRemovePrefixIfExist(String removePrefixIfExist) {
        config.setRemovePrefixIfExist(removePrefixIfExist);
        return this;
    }

    @Override
    protected GlobalConfig configureContext() {
        return config.setExporter(CodeExporter.class)
                .setExtractor(SourceCodeExtractor.class)
                .setResolver(AntlrThriftResolver.class);
    }

}
