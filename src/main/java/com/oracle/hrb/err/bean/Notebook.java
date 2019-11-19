package com.oracle.hrb.err.bean;

import org.junit.Test;

public class Notebook {
    private String id;
    private String code;
    private String name;
    private Test desc;



    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", desc=" + desc +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Test getDesc() {
        return desc;
    }

    public void setDesc(Test desc) {
        this.desc = desc;
    }
}
