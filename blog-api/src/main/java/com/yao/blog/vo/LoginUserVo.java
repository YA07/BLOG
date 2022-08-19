package com.yao.blog.vo;

/**
 * @version 1.0
 * @Description TODO
 * @Author 17
 * @date 2022/8/19 22:26
 **/
import lombok.Data;

@Data
public class LoginUserVo {

    private Long id;

    private String account;

    private String nickname;

    private String avatar;
}
