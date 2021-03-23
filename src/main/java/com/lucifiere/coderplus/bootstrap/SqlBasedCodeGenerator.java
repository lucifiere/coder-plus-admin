package com.lucifiere.coderplus.bootstrap;

import com.lucifiere.coderplus.common.GlobalConfig;
import com.lucifiere.coderplus.exporter.CodeExporter;
import com.lucifiere.coderplus.extract.table.CustomizedModelNamedExtractor;
import com.lucifiere.coderplus.extract.table.SourceCodeExtractor;
import com.lucifiere.coderplus.resovler.antlr.AntlrSqlResolver;

/**
 * 预制好组件实现类型的启动器
 *
 * @author XD.Wang
 */
public class SqlBasedCodeGenerator extends Bootstrap {

    protected final GlobalConfig config = new GlobalConfig();

    public SqlBasedCodeGenerator setWorkspacePath(String workspacePath) {
        config.setWorkspacePath(workspacePath);
        return this;
    }

    public SqlBasedCodeGenerator setOutputDir(String outputPath) {
        config.setOutputDir(outputPath);
        return this;
    }

    public SqlBasedCodeGenerator setDdlName(String ddlName) {
        config.setInputFileName(ddlName);
        return this;
    }

    public SqlBasedCodeGenerator setTemplatesPath(String templatesPath) {
        config.setTemplatesConfigScanPath(templatesPath);
        return this;
    }

    public SqlBasedCodeGenerator setRemovePrefixIfExist(String removePrefixIfExist) {
        config.setRemovePrefixIfExist(removePrefixIfExist);
        return this;
    }

    public SqlBasedCodeGenerator setAuthor(String author) {
        config.setAuthor(author);
        return this;
    }

    public SqlBasedCodeGenerator setPkg(String pkg) {
        config.setBasePkg(pkg);
        return this;
    }

    public SqlBasedCodeGenerator setDriveCode(String code) {
        config.setDriveCode(code);
        return this;
    }

    @Override
    protected GlobalConfig configureContext() {
        return config.setExporter(CodeExporter.class)
                .setExtractor(SourceCodeExtractor.class)
                .setResolver(AntlrSqlResolver.class);
    }

}
