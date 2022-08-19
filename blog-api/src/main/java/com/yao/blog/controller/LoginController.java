package com.yao.blog.controller;

import com.yao.blog.service.LoginService;
import com.yao.blog.vo.Result;
import com.yao.blog.vo.params.LoginParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @Description TODO 登录
 * @Author 17
 * @date 2022/8/19 19:08
 **/
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public Result login(@RequestBody LoginParams loginParams){
        return loginService.login(loginParams);

    }
}
