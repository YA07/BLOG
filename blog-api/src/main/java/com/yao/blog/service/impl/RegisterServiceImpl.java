package com.yao.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.yao.blog.dao.pojo.SysUser;
import com.yao.blog.service.RegisterService;
import com.yao.blog.service.SysUserService;
import com.yao.blog.utils.JWTUtils;
import com.yao.blog.vo.ErrorCode;
import com.yao.blog.vo.Result;
import com.yao.blog.vo.params.RegisterParams;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @Description TODO
 * @Author 17
 * @date 2022/8/20 11:29
 **/
@Service
@Transactional
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String salt = "115080";

    /*
     * @Description //TODO 注册
     * 1 判断参数是否合法
     * 2 判断账户是否存在
     * 3 不存在 注册账户
     * 4 生成token
     * 5 存入Redis 并返回
     * 6 加上事务，一旦中间过程出现问题，注册用户需要回滚
     **/
    @Override
    public Result register(RegisterParams registerParams) {
        String account = registerParams.getAccount();
        String password = registerParams.getPassword();
        String nickname = registerParams.getNickname();

        if(StringUtils.isBlank(account) || StringUtils.isBlank(password) || StringUtils.isBlank(nickname)){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        SysUser sysUser = sysUserService.findUserByAccount(account);
        if(sysUser != null){
            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(), ErrorCode.ACCOUNT_EXIST.getMsg());
        }

        sysUser = new SysUser();
        sysUser.setNickname(nickname);
        sysUser.setAccount(account);
        sysUser.setPassword(DigestUtils.md5Hex(password+salt));
        sysUser.setCreateDate(System.currentTimeMillis());
        sysUser.setLastLogin(System.currentTimeMillis());
        sysUser.setAvatar("/static/img/logo.b3a48c0.png");
        sysUser.setAdmin(1); //1 为true
        sysUser.setDeleted(0); // 0 为false
        sysUser.setSalt("");
        sysUser.setStatus("");
        sysUser.setEmail("");
        this.sysUserService.save(sysUser);

        String token = JWTUtils.createToken(sysUser.getId());
        //System.out.println(token);
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(sysUser),1, TimeUnit.DAYS);
        return Result.success(token);
    }
}
