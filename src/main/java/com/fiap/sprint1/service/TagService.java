package com.fiap.sprint1.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fiap.sprint1.exception.RecursoLigadoAOutroException;
import com.fiap.sprint1.exception.RecursoNaoEncontradoException;
import com.fiap.sprint1.model.Tag;
import com.fiap.sprint1.model.dto.TagDto;
import com.fiap.sprint1.repository.MotoRepository;
import com.fiap.sprint1.repository.TagRepository;

@Service
public class TagService {

    private final MotoRepository motoRepository;
    private final TagRepository tagRepository;

    public TagService(MotoRepository motoRepository, TagRepository tagRepository) {
        this.motoRepository = motoRepository;
        this.tagRepository = tagRepository;
    }

    public TagDto saveTag(TagDto dto) {
        Tag tag = toEntity(dto);
        return toDto(tagRepository.save(tag));
    }

    public TagDto getTagById(String id) {
        return tagRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    public void deleteTag(String id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Tag com ID " + id + " não encontrada."));

        if (motoRepository.existsByTag(tag)) {
            throw new RecursoLigadoAOutroException("A tag está associada a uma ou mais motos e não pode ser deletada.");
        }

        tagRepository.delete(tag);
    }

    public Page<TagDto> getAllTags(Pageable pageable) {
        return tagRepository.findAll(pageable)
                .map(this::toDto);
    }

    public TagDto updateTag(String id, TagDto dto) {
        if (tagRepository.existsById(id)) {
            Tag tag = toEntity(dto);
            tag.setId(id);
            return toDto(tagRepository.save(tag));
        }
        return null;
    }

    public Page<TagDto> getTagsBySinal(Long sinal) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("sinal").descending());
        return tagRepository.findBySinalLessThanEqual(sinal, pageable)
                .map(this::toDto);
    }

    // toDto
    public TagDto toDto(Tag tag) {
        TagDto dto = new TagDto();
        dto.setId(tag.getId());
        dto.setSinal(tag.getSinal());
        return dto;
    }

    // toEntity
    public Tag toEntity(TagDto dto) {
        Tag tag = new Tag();
        tag.setId(dto.getId());
        tag.setSinal(dto.getSinal());
        return tag;
    }
}
