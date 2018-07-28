package com.taotao.rest.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemCat {
    @JsonProperty("n")
    private String name;

    @JsonProperty("u")
    private String url;

    @JsonProperty("d")
    private String data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
