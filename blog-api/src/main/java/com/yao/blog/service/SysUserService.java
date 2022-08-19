package com.yao.blog.service;

import com.yao.blog.dao.pojo.SysUser;
import com.yao.blog.vo.Result;

public interface SysUserService {
    SysUser findUserById(Long id);

    SysUser findUser(String accout, String password);

    Result findUserByToken(String token);
}
