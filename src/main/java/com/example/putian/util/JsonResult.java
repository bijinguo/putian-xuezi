package com.example.putian.util;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class JsonResult<T> {

    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    private Integer state;
    @JsonInclude(value= JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(value= JsonInclude.Include.ALWAYS)
    private T data;

    public JsonResult() {
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(String message) {
        this.message = message;
    }

    public JsonResult(Integer state, T data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult(Integer state, String message, T data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
