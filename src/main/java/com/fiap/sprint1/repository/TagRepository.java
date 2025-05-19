package com.fiap.sprint1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.sprint1.model.Tag;

public interface TagRepository extends JpaRepository<Tag, String> {
}
