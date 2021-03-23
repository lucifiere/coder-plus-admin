package com.lucifiere.coderplusadmin.module;

import cn.hutool.core.map.MapUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 菜单
 *
 * @author created by XD.Wang
 * Date 2021/3/22.
 */
public enum CodeGenerateNodeSetting {

    /**
     * SQL-DDL驱动
     */
    DDL("/dashboard/sql-ddl", "SQL-DDL驱动"),
    /**
     * Thrift-IDL驱动
     */
    IDL("/dashboard/thrift-idl", "Thrift-IDL驱动");

    CodeGenerateNodeSetting(String url, String text) {
        this.url = url;
        this.text = text;
    }

    private final String url;

    private final String text;

    public String getUrl() {
        return url;
    }

    public String getText() {
        return text;
    }

    public static List<Map<String, String>> toMap() {
        CodeGenerateNodeSetting[] all = CodeGenerateNodeSetting.values();
        return Arrays.stream(all).map(it -> {
            Map<String, String> r = MapUtil.newHashMap();
            r.put("text", it.getText());
            r.put("url", it.getUrl());
            r.put("nodeType", it.name());
            return r;
        }).collect(Collectors.toList());
    }

    public static Map<String, String> toMap(CodeGenerateNodeSetting m) {
        Map<String, String> r = MapUtil.newHashMap();
        r.put("text", m.getText());
        r.put("url", m.getUrl());
        r.put("nodeType", m.name());
        return r;
    }

}
