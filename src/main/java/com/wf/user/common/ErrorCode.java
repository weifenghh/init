package com.wf.user.common;

/**
 * @Author 玉米排骨汤
 * @Date 2023/12/30 12:15
 * @Package com.wf.user.common
 * @Version 1.0
 * @Since 1.0
 */
public enum ErrorCode {

    SYSTEM_ERROR(20000,"系统错误");

    private final int code;
private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
