package com.wf.user.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author 玉米排骨汤
 * @Date 2023/12/31 9:25
 * @Package com.wf.user.model.dto.user
 * @Version 1.0
 * @Since 1.0
 */

@Data
public class UserRegisterDTO implements Serializable {

    private String userAccount;
    private String userPassword;

    private static final long serialVersionUID = 1L;

}
