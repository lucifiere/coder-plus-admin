package com.lucifiere.coderplus.model.loader;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.StaticLog;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Properties;

/**
 * 基于配置文件的属性加载
 *
 * @author created by XD.Wang
 * Date 2020/9/4.
 */
public class PropertiesAttrLoader extends DefaultGlobalAttrLoader {

    private static volatile PropertiesAttrLoader LOADER;

    private static final Properties PROPERTIES = new Properties();

    private static final String SETTING_FILE = "/config/setting.properties";

    private static final String AUTHOR_PROPERTIES_KEY = "author";

    private static final String BASE_PACKAGE_PROPERTIES_KEY = "base.package";

    private static final String CUSTOMIZED_MODEL_PROPERTIES_KEY = "customized.model.name";

    static {
        try {
            InputStream is = PropertiesAttrLoader.class.getResourceAsStream(SETTING_FILE);
            InputStreamReader r = new InputStreamReader(is, StandardCharsets.UTF_8);
            PROPERTIES.load(r);
        } catch (Exception e) {
            StaticLog.error("配置文件载入失败！",e);
        }
    }

    protected PropertiesAttrLoader() {
    }

    public static PropertiesAttrLoader getLoader() {
        if (LOADER == null) {
            LOADER = new PropertiesAttrLoader();
        }
        return LOADER;
    }

    @Override
    public String getAuthorSetting() {
        return Optional.ofNullable(PROPERTIES.getProperty(AUTHOR_PROPERTIES_KEY)).filter(StrUtil::isNotBlank).orElse(super.getAuthorSetting());
    }

    @Override
    public String getCustomizedModelSetting() {
        return Optional.ofNullable(PROPERTIES.getProperty(CUSTOMIZED_MODEL_PROPERTIES_KEY)).filter(StrUtil::isNotBlank).orElse(super.getCustomizedModelSetting());
    }

    @Override
    public String getBasePackageSetting() {
        return Optional.ofNullable(PROPERTIES.getProperty(BASE_PACKAGE_PROPERTIES_KEY)).filter(StrUtil::isNotBlank).orElse(super.getBasePackageSetting());
    }

}
