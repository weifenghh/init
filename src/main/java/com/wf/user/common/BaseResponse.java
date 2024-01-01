package com.wf.user.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author 玉米排骨汤
 * @Date 2023/12/30 11:52
 * @Package com.wf.user.common
 * @Version 1.0
 * @Since 1.0
 */
@Data
public class BaseResponse<T> implements Serializable {

    private T data;
    private String message;
    private int code;

    public BaseResponse(T data, String message, int code){
        this.data = data;
        this.message = message;
        this.code = code;
    }

    /**
     * 成功的响应
     */
    public BaseResponse(T data, int code){
        this(data,"",code);
    }

    /**
     * 失败的响应
     */
    public BaseResponse(String message, int code){
        this(null,message,code);
    }

}
