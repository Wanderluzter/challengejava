package com.fiap.sprint1.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fiap.sprint1.exception.RecursoLigadoAOutroException;
import com.fiap.sprint1.exception.RecursoNaoEncontradoException;
import com.fiap.sprint1.model.Tag;
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

    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag getTagById(String id) {
        return tagRepository.findById(id).orElse(null);
    }

    public void deleteTag(String id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Tag com ID " + id + " não encontrada."));

        if (motoRepository.existsByTag(tag)) {
            throw new RecursoLigadoAOutroException("A tag está associada a uma ou mais motos e não pode ser deletada.");
        }

        tagRepository.delete(tag);
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

    public Page<Tag> getTagsBySinal(Long sinal) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("sinal").descending());
        return tagRepository.findBySinalLessThanEqual(sinal, pageable);
    }
}
