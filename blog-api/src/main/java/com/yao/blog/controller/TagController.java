package com.yao.blog.controller;

import com.yao.blog.service.TagService;
import com.yao.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @Description TODO
 * @Author 17
 * @date 2022/8/19 13:38
 **/
@RestController
@RequestMapping("tags")
public class TagController {

    @Autowired
    private TagService tagService;
    //
    @GetMapping("/hot")
    public Result hot(){
        int limit = 6;
        return tagService.hots(limit);
    }

}
