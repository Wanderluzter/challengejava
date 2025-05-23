package com.fiap.sprint1.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.sprint1.model.Moto;
import com.fiap.sprint1.model.Tag;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long> {

    Page<Moto> findByPlaca(String placa, Pageable pageable);

    Page<Moto> findByDataEntrada(LocalDate dataEntrada, Pageable pageable);

    Page<Moto> findByDataEntradaGreaterThanEqualOrderByDataEntradaDesc(LocalDate dataEntrada, Pageable pageable);

    public boolean existsByTag(Tag tag);
}
