package com.lucifiere.coderplusadmin.domain;

/**
 * 代码生成入参
 *
 * @author created by XD.Wang
 * Date 2021/3/22.
 */
public class CodeGenerateRequest {

    private String driveCode;

    private String nodeType;

    private String author;

    private String pkg;

    private String customizedModelName;

    public String getDriveCode() {
        return driveCode;
    }

    public void setDriveCode(String driveCode) {
        this.driveCode = driveCode;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public String getCustomizedModelName() {
        return customizedModelName;
    }

    public void setCustomizedModelName(String customizedModelName) {
        this.customizedModelName = customizedModelName;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }
}
