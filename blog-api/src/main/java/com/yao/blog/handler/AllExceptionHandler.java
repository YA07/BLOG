package com.yao.blog.handler;

import com.yao.blog.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @version 1.0
 * @Description TODO 统一异常处理
 * @Author 17
 * @date 2022/8/19 15:27
 **/
//对加了controller注解的方法进行拦截处理，aop原理
@ControllerAdvice
public class AllExceptionHandler {
    //进行异常处理，处理Exception.class的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody//返回Jason数据， 不加的话就返回页面
    public Result doException(Exception e){
        e.printStackTrace();
        return Result.fail(-999,"系统异常");
    }

}
