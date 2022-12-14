package com.yao.blog.service.impl;

import com.yao.blog.dao.mapper.TagMapper;
import com.yao.blog.dao.pojo.Tag;
import com.yao.blog.service.TagService;
import com.yao.blog.vo.Result;
import com.yao.blog.vo.TagVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @version 1.0
 * @Description TODO
 * @Author 17
 * @date 2022/8/18 22:45
 **/
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    public TagVo copy(Tag tag){
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag,tagVo);
        return tagVo;
    }
    public List<TagVo> copyList(List<Tag> tagList){
        List<TagVo> tagVoList = new ArrayList<>();
        for (Tag tag : tagList) {
            tagVoList.add(copy(tag));
        }
        return tagVoList;
    }

    @Override
    public List<TagVo> findTagsByArticleId(Long articleId) {
        //mybatisplus无法进行多表查询
        List<Tag> tags = tagMapper.findTagsByArticleId(articleId);
        return copyList(tags);
    }

    @Override
    public Result hots(int limit) {
        //标签所拥有的文章数量最多
        //根据tag_id分组排列计数
        List<Long> tagIds = tagMapper.findHotsTagIds(limit);

        if(CollectionUtils.isEmpty(tagIds)){
            return Result.success(Collections.emptyList());
        }


        List<Tag> tagList = tagMapper.finTagsByTagIds(tagIds);

        return Result.success(tagList);
    }


}
