package com.yao.blog.service;

import com.yao.blog.vo.Result;
import com.yao.blog.vo.params.RegisterParams;

public interface RegisterService {
    Result register(RegisterParams registerParams);
}
