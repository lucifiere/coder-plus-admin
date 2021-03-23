package com.lucifiere.coderplus.extract.table;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.lucifiere.coderplus.container.ManagedBean;
import com.lucifiere.coderplus.io.NioTextFileAccessor;
import com.lucifiere.coderplus.model.Model;

import java.util.Optional;

/**
 * 源文件提取器
 *
 * @author XD.Wang
 * Date 2020-7-25.
 */
@ManagedBean
public class SourceCodeExtractor extends CustomizedAttrExtractor {

    @Override
    public Model extract() {
        String ddlStr;
        if (globalContext.getConfig().getDriveCode() == null) {
            ddlStr = NioTextFileAccessor.loadFile(Joiner.on("/").join(globalContext.getConfig().getWorkspacePath(), globalContext.getConfig().getInputFileName()));
        } else {
            ddlStr = globalContext.getConfig().getDriveCode();
        }
        Preconditions.checkArgument(StrUtil.isNotBlank(ddlStr));
        Model model = globalContext.calByComponent(globalContext.getConfig().getResolver(), resolver -> resolver.resolve(ddlStr));
        Optional.ofNullable(globalContext.getConfig().getRemovePrefixIfExist()).ifPresent(prefix -> model.setKeyword(model.getKeyword().replaceAll(prefix, "")));
        model.merge(super.extract());
        return model;
    }

}
