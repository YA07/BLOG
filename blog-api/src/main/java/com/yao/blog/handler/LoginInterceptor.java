package com.yao.blog.handler;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @version 1.0
 * @Description TODO
 * @Author 17
 * @date 2022/8/20 19:14
 **/
public class LoginInterceptor  implements HandlerInterceptor {
    //在执行controller方法（Handler）之前进行执行
    //1 需要判断请求接口路径是否为HandlerMethod(controller方法)
    //2 判断token是否为空，若空，未登录
    //3 不为空，验证登录loginservice checkToken
    //4 若认证成功，放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
