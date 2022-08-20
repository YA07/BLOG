package com.yao.blog.controller;

import com.yao.blog.service.RegisterService;
import com.yao.blog.vo.Result;
import com.yao.blog.vo.params.RegisterParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @Description TODO
 * @Author 17
 * @date 2022/8/20 11:27
 **/
@RestController
@RequestMapping("register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping
    public Result register(@RequestBody RegisterParams registerParams){
        return registerService.register(registerParams);
    }
}
