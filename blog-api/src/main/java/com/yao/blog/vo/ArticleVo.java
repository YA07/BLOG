package com.yao.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @version 1.0
 * @Description TODO
 * @Author 17
 * @date 2022/8/18 13:54
 **/
@Data
public class ArticleVo {

    private Long id;

    private String title;

    private String summary;

    private int commentCounts;

    private int viewCounts;

    private int weight;
    /**
     * 创建时间
     */
    private String createDate;

    private String author;

    //private ArticleBodyVo body;

    private List<TagVo> tags;

    //private List<CategoryVo> categorys;

}