package com.wf.user.exception;

/**
 * @Author 玉米排骨汤
 * @Date 2023/12/30 12:05
 * @Package com.wf.user.exception
 * @Version 1.0
 * @Since 1.0
 */
public class BusinessException extends RuntimeException{

private final int code;

    public BusinessException(String message,int code) {
        super(message);
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
