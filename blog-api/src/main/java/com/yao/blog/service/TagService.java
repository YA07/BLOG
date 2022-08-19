package com.yao.blog.service;

import com.yao.blog.vo.Result;
import com.yao.blog.vo.TagVo;

import java.util.List;

public interface TagService {

    List<TagVo> findTagsByArticleId(Long articleId);

    //查询最热的标签前n条
    Result hots(int limit);
}
