package com.yao.blog.vo.params;

import lombok.Data;

/**
 * @version 1.0
 * @Description TODO
 * @Author 17
 * @date 2022/8/20 11:25
 **/
@Data
public class RegisterParams {
    private String account;
    private String password;
    private String nickname;
}
