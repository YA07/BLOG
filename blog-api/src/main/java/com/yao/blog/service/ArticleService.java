package com.yao.blog.service;

import com.yao.blog.vo.ArticleVo;
import com.yao.blog.vo.Result;
import com.yao.blog.vo.params.PageParams;

import java.util.List;

public interface ArticleService {
    /*
     * @Description //TODO 分页查询文章列表
     * @Date 13:40 2022/8/18
     * @Param 
     * @return 
     **/
    Result listArticle(PageParams pageParams);

    /*
     * @Description //TODO 取出最热文章
     * @Date 15:50 2022/8/19
     * @Param [limit]
     * @return com.yao.blog.vo.Result
     **/
    Result hotArticle(int limit);

    /*
     * @Description //TODO 取出最新五篇文章
     * @Date 16:40 2022/8/19
     * @Param [limit]
     * @return com.yao.blog.vo.Result
     **/
    Result newArticle(int limit);

    /*
     * @Description //TODO 文章归档
     * @Date 19:12 2022/8/19
     * @Param []
     * @return com.yao.blog.vo.Result
     **/
    Result listArchives();

    //List<ArticleVo> listArticlesPage(PageParams pageParams);
}
