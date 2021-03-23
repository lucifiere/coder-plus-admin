package com.lucifiere.coderplusadmin.domain;

/**
 * 代码生成入参
 *
 * @author created by XD.Wang
 * Date 2021/3/22.
 */
public class CodeGenerateResponse {

    private Boolean suc;

    private String msg;

    private String model;

    private String mapper;

    private String repository;

    private String repositoryImpl;

    private String xml;

    private String example;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMapper() {
        return mapper;
    }

    public void setMapper(String mapper) {
        this.mapper = mapper;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getRepositoryImpl() {
        return repositoryImpl;
    }

    public void setRepositoryImpl(String repositoryImpl) {
        this.repositoryImpl = repositoryImpl;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public Boolean getSuc() {
        return suc;
    }

    public void setSuc(Boolean suc) {
        this.suc = suc;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
