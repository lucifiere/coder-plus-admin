package com.lucifiere.coderplus.common;

import com.lucifiere.coderplus.exporter.Exporter;
import com.lucifiere.coderplus.extract.Extractor;
import com.lucifiere.coderplus.resovler.Resolver;

/**
 * 全局配置
 *
 * @author created by XD.Wang
 * Date 2020/7/12.
 */
public class GlobalConfig {

    private String workspacePath;
    private String outputDir;
    private String inputFileName;
    private String driveCode;
    private String templatesConfigScanPath;
    private String groupsConfigScanPath;
    private String removePrefixIfExist;
    private String author;
    private String basePkg;
    private String customizedModelName;
    private Class<? extends Resolver> resolver;
    private Class<? extends Extractor> extractor;
    private Class<? extends Exporter> exporter;

    public String getWorkspacePath() {
        return workspacePath;
    }

    public GlobalConfig setWorkspacePath(String workspacePath) {
        this.workspacePath = workspacePath;
        return this;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public GlobalConfig setOutputDir(String outputDir) {
        this.outputDir = outputDir;
        return this;
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public GlobalConfig setInputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
        return this;
    }

    public String getTemplatesConfigScanPath() {
        return templatesConfigScanPath;
    }

    public GlobalConfig setTemplatesConfigScanPath(String templatesConfigScanPath) {
        this.templatesConfigScanPath = templatesConfigScanPath;
        return this;
    }

    public String getGroupsConfigScanPath() {
        return groupsConfigScanPath;
    }

    public GlobalConfig setGroupsConfigScanPath(String groupsConfigScanPath) {
        this.groupsConfigScanPath = groupsConfigScanPath;
        return this;
    }

    public String getRemovePrefixIfExist() {
        return removePrefixIfExist;
    }

    public GlobalConfig setRemovePrefixIfExist(String removePrefixIfExist) {
        this.removePrefixIfExist = removePrefixIfExist;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public GlobalConfig setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getBasePkg() {
        return basePkg;
    }

    public GlobalConfig setBasePkg(String basePkg) {
        this.basePkg = basePkg;
        return this;
    }

    public String getCustomizedModelName() {
        return customizedModelName;
    }

    public GlobalConfig setCustomizedModelName(String customizedModelName) {
        this.customizedModelName = customizedModelName;
        return this;
    }

    public Class<? extends Resolver> getResolver() {
        return resolver;
    }

    public GlobalConfig setResolver(Class<? extends Resolver> resolver) {
        this.resolver = resolver;
        return this;
    }

    public Class<? extends Extractor> getExtractor() {
        return extractor;
    }

    public GlobalConfig setExtractor(Class<? extends Extractor> extractor) {
        this.extractor = extractor;
        return this;
    }

    public Class<? extends Exporter> getExporter() {
        return exporter;
    }

    public GlobalConfig setExporter(Class<? extends Exporter> exporter) {
        this.exporter = exporter;
        return this;
    }

    public String getDriveCode() {
        return driveCode;
    }

    public GlobalConfig setDriveCode(String driveCode) {
        this.driveCode = driveCode;
        return this;
    }
}


