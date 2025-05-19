package com.fiap.sprint1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.fiap.sprint1.model.Tag;
import com.fiap.sprint1.service.TagService;


@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    public Page<Tag> getTags(Pageable pageable) {
        return tagService.getAllTags(pageable);
    }
    

}
