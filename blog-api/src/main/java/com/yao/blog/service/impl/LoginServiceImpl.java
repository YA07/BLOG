package com.yao.blog.service.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.qiniu.util.Json;
import com.yao.blog.dao.pojo.SysUser;
import com.yao.blog.service.LoginService;
import com.yao.blog.service.SysUserService;
import com.yao.blog.utils.JWTUtils;
import com.yao.blog.vo.ErrorCode;
import com.yao.blog.vo.Result;
import com.yao.blog.vo.params.LoginParams;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @Description TODO
 * @Author 17
 * @date 2022/8/19 19:11
 **/

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //加密盐
    private static final String slat = "115080";

    @Override
    public Result login(LoginParams loginParams) {
        //1 檢查參數是否合法
        //2 根据用户名和密码去数据库查询是否存在
        //3 若不存在
        //4 若存在
        //5 token放redis中，redis token:user信息  登录认证的时候，先认证token字符串是否合法，去redis认证是否存在
        String account = loginParams.getAccount();
        String password = loginParams.getPassword();
        //System.out.println("login() "+account);

        if(StringUtils.isBlank(account) || StringUtils.isBlank(password)){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }

        password = DigestUtils.md5Hex(password+slat);
        //System.out.println("login() "+password);
        SysUser sysUser = sysUserService.findUser(account,password);
        if(sysUser == null){
            //System.out.println("sysUser == null");
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        //System.out.println("sysUser != null");
        String token = JWTUtils.createToken(sysUser.getId());
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(sysUser),1, TimeUnit.DAYS);
        return Result.success(token);

    }

    @Override
    public SysUser checkToken(String token) {
        if(StringUtils.isBlank(token)){
            return null;
        }
        Map<String,Object> stringObjectMap = JWTUtils.checkToken(token);
        if(stringObjectMap == null){
            return null;
        }
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if(StringUtils.isBlank(userJson)){
            return null;
        }
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        return sysUser;
    }

    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_"+token);
        return Result.success(null);
    }
}
