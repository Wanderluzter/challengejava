package com.fiap.sprint1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.sprint1.model.Tag;

public interface TagRepository extends JpaRepository<Tag, String> {

    Page<Tag> findBySinalLessThanEqual(Long sinal, Pageable pageable);
}
