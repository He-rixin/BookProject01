package com.test.util;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class JsonResponseBody<T> {
    private Integer code;
    private String msg;
    private T data;
    private Long total;


    public JsonResponseBody(){
        this.code=JsonResponseStatus.SUCCESS.getCode();
        this.msg=JsonResponseStatus.SUCCESS.getMsg();
    }
    public JsonResponseBody(T data){
        this.code=JsonResponseStatus.SUCCESS.getCode();
        this.msg=JsonResponseStatus.SUCCESS.getMsg();
        this.data=data;
    }

    public JsonResponseBody(T data,Long total){
        this.code=JsonResponseStatus.SUCCESS.getCode();
        this.msg=JsonResponseStatus.SUCCESS.getMsg();
        this.data=data;
        this.total=total;
    }

    public JsonResponseBody(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public JsonResponseBody(Integer code,String msg,T data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

    public JsonResponseBody(Integer code,String msg,T data,Long total){
        this.code=code;
        this.msg=msg;
        this.data=data;
        this.total=total;
    }





}
