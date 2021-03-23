package com.lucifiere.coderplus.model.loader;

import cn.hutool.core.util.StrUtil;
import com.lucifiere.coderplus.container.ManagedBean;
import com.lucifiere.coderplus.utils.GlobalContextHolder;

import java.util.Optional;

/**
 * 基于配置文件的属性加载
 *
 * @author created by XD.Wang
 * Date 2020/9/4.
 */
@ManagedBean
public class GlobalConfigAttrLoader extends PropertiesAttrLoader {

    private static volatile GlobalConfigAttrLoader LOADER;

    protected GlobalConfigAttrLoader() {
    }

    public static GlobalConfigAttrLoader getLoader() {
        if (LOADER == null) {
            LOADER = new GlobalConfigAttrLoader();
        }
        return LOADER;
    }

    @Override
    public String getAuthorSetting() {
        return Optional.ofNullable(GlobalContextHolder.globalContext.getConfig().getAuthor()).filter(StrUtil::isNotBlank).orElse(super.getAuthorSetting());
    }

    @Override
    public String getCustomizedModelSetting() {
        return Optional.ofNullable(GlobalContextHolder.globalContext.getConfig().getCustomizedModelName()).filter(StrUtil::isNotBlank).orElse(super.getCustomizedModelSetting());
    }

    @Override
    public String getBasePackageSetting() {
        return Optional.ofNullable(GlobalContextHolder.globalContext.getConfig().getBasePkg()).filter(StrUtil::isNotBlank).orElse(super.getBasePackageSetting());
    }

}
