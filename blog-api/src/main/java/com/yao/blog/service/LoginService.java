package com.yao.blog.service;

import com.yao.blog.dao.pojo.SysUser;
import com.yao.blog.vo.Result;
import com.yao.blog.vo.params.LoginParams;

public interface LoginService {
    //登录
    Result login(LoginParams loginParams);

    SysUser checkToken(String token);

    //退出登录
    Result logout(String token);
}
