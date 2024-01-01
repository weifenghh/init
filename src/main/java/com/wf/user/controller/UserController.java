package com.wf.user.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wf.user.annotation.AuthCheck;
import com.wf.user.common.BaseResponse;
import com.wf.user.common.ResultUtils;
import com.wf.user.exception.BusinessException;
import com.wf.user.model.dto.user.UserRegisterDTO;
import com.wf.user.model.entity.User;
import com.wf.user.model.vo.user.UserVO;
import com.wf.user.service.UserService;
import com.wf.user.utils.jwt.JwtUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 玉米排骨汤
 * @Date 2023/12/30 10:23
 * @Package com.wf.user.controller
 * @Version 1.0
 * @Since 1.0
 */

@RestController
@RequestMapping("/user")
@Slf4j
@Api("userController(用户登录)")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户登录
     * @param user
     * @return
     */
    @PostMapping("/userLogin")
    public BaseResponse<Map<String,Object>> userLogin(@RequestBody User user){
        String userAccount = user.getUserAccount();
        String userPassword = user.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount,userPassword)) {
            throw new BusinessException("用户名或密码为空",40000);
        }
        if(!(userAccount.length() >= 2 && userAccount.length() <= 6)){
            throw new BusinessException("用户名长度要在2~6位数",40000);
        }
        if(!(userPassword.length() >= 6 && userPassword.length() <= 10)){
            throw new BusinessException("密码长度要在6~10位数", 40000);
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
        userQueryWrapper.eq("userAccount",userAccount).eq("userPassword",userPassword);
        User newUser = userService.getOne(userQueryWrapper);
        if(newUser == null){
            throw new BusinessException("未找到此用户，请重试",40000);
        }
        //用户脱敏
        UserVO userVO = BeanUtil.copyProperties(newUser,UserVO.class);
        String jwtToken = JwtUtil.getJwtToken(userVO.getId(), userVO.getUserAccount());

        Map<String, Object> loginMap = new HashMap<>();
        loginMap.put("jwtToken",jwtToken);
        loginMap.put("user",userVO);
        return ResultUtils.success(loginMap);
    }

    /**
     * 用户注册
     * @return
     */
    @PostMapping("/userRegister")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterDTO userRegisterDTO){
        String userAccount = userRegisterDTO.getUserAccount();
        String userPassword = userRegisterDTO.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount,userPassword)) {
            throw new BusinessException("用户名或密码为空",40000);
        }
        if(!(userAccount.length() >= 2 && userAccount.length() <= 6)){
            throw new BusinessException("用户名长度要在2~6位数",40000);
        }
        if(!(userPassword.length() >= 6 && userPassword.length() <= 10)){
            throw new BusinessException("密码长度要在6~10位数", 40000);
        }
        User user = BeanUtil.copyProperties(userRegisterDTO,User.class);
        System.out.print(user);
        boolean flag = userService.save(user);
        if(!flag){
            throw new BusinessException("系统异常,用户注册失败",40000);
        }
        return ResultUtils.success(user.getId());
    }



}
