package com.lucifiere.coderplus.bootstrap;

import com.lucifiere.coderplus.common.GlobalConfig;

/**
 * 公共参数配置
 *
 * @author created by XD.Wang
 * Date 2021/3/24.
 */
public abstract class ConfigurableBootstrap extends Bootstrap {

    protected final GlobalConfig config = new GlobalConfig();

    public ConfigurableBootstrap setInputFileName(String name) {
        config.setInputFileName(name);
        return this;
    }

    public ConfigurableBootstrap setWorkspacePath(String workspacePath) {
        config.setWorkspacePath(workspacePath);
        return this;
    }

    public ConfigurableBootstrap setOutputDir(String outputPath) {
        config.setOutputDir(outputPath);
        return this;
    }


    public ConfigurableBootstrap setAuthor(String author) {
        config.setAuthor(author);
        return this;
    }

    public ConfigurableBootstrap setPkg(String pkg) {
        config.setBasePkg(pkg);
        return this;
    }

    public ConfigurableBootstrap setDriveCode(String code) {
        config.setDriveCode(code);
        return this;
    }

    public ConfigurableBootstrap setTemplatesPath(String templatesPath) {
        config.setTemplatesConfigScanPath(templatesPath);
        return this;
    }

    public ConfigurableBootstrap setRemovePrefixIfExist(String removePrefixIfExist) {
        config.setRemovePrefixIfExist(removePrefixIfExist);
        return this;
    }

}
