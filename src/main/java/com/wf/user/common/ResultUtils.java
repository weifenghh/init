package com.wf.user.common;

/**
 * @Author 玉米排骨汤
 * @Date 2023/12/30 11:57
 * @Package com.wf.user.common
 * @Version 1.0
 * @Since 1.0
 */
public class ResultUtils {

    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>(data,200);
    }

    public static BaseResponse error(String message, int code){
        return new BaseResponse(message,code);
    }

}
