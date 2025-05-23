package com.fiap.sprint1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.sprint1.model.dto.TagDto;
import com.fiap.sprint1.service.TagService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    public ResponseEntity<Page<TagDto>> getTags(Pageable pageable) {
        Page<TagDto> tags = tagService.getAllTags(pageable);
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDto> getTag(@PathVariable String id) {
        TagDto tag = tagService.getTagById(id);
        if (tag != null) {
            return ResponseEntity.ok(tag);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TagDto> postTag(@Valid @RequestBody TagDto tagDto) {
        TagDto savedTag = tagService.saveTag(tagDto);
        return ResponseEntity.ok(savedTag);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagDto> putTag(@PathVariable String id, @Valid @RequestBody TagDto tagDto) {
        TagDto updatedTag = tagService.updateTag(id, tagDto);
        if (updatedTag != null) {
            return ResponseEntity.ok(updatedTag);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable String id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build(); // 204 No Content se deletar com sucesso
    }

    // Busca por sinal com paginação e ordenação decrescente por "sinal"
    @GetMapping("/sinal/{sinal}")
    public ResponseEntity<Page<TagDto>> getTagsBySinal(@PathVariable Long sinal) {
        Page<TagDto> tags = tagService.getTagsBySinal(sinal);
        return ResponseEntity.ok(tags);
    }
}
