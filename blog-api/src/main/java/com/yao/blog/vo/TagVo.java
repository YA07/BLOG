package com.yao.blog.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @version 1.0
 * @Description TODO
 * @Author 17
 * @date 2022/8/18 13:57
 **/
@Data
public class TagVo{
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String tagName;

    private String avatar;

}