package com.yao.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yao.blog.dao.dos.Archives;
import com.yao.blog.dao.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.Resource;
import java.util.List;


public interface ArticleMapper extends BaseMapper<Article> {

    List<Archives> listArchives();
}
