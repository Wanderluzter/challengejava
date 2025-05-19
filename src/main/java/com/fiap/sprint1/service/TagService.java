package com.fiap.sprint1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiap.sprint1.model.Tag;
import com.fiap.sprint1.repository.TagRepository;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public void saveTag(Tag tag) {
        tagRepository.save(tag);
    }

    public Tag getTagById(String id) {
        return tagRepository.findById(id).orElse(null);
    }

    public void deleteTag(String id) {
        tagRepository.deleteById(id);
    }

    public Page<Tag> getAllTags(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    public Tag updateTag(String id, Tag tag) {
        if (tagRepository.existsById(id)) {
            tag.setId(id);
            return tagRepository.save(tag);
        }
        return null;
    }
}
