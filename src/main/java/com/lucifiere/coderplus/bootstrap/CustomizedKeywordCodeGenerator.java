package com.lucifiere.coderplus.bootstrap;

import com.lucifiere.coderplus.common.GlobalConfig;
import com.lucifiere.coderplus.exporter.CodeExporter;
import com.lucifiere.coderplus.extract.table.CustomizedModelNamedExtractor;
import com.lucifiere.coderplus.resovler.antlr.AntlrSqlResolver;

/**
 * 预制好组件实现类型的启动器
 *
 * @author XD.Wang
 */
public class CustomizedKeywordCodeGenerator extends SqlBasedCodeGenerator {

    public void setCustomizedModelName(String name) {
        config.setCustomizedModelName(name);
    }

    @Override
    protected GlobalConfig configureContext() {
        return config.setExporter(CodeExporter.class)
                .setExtractor(CustomizedModelNamedExtractor.class)
                .setResolver(AntlrSqlResolver.class);
    }

}
