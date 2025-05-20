package com.fiap.sprint1.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.sprint1.model.Moto;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long> {

    Page<Moto> findByPlaca(String placa, Pageable pageable);

    Page<Moto> findByDataEntrada(LocalDate dataEntrada, Pageable pageable);

    // Busca personalizada por data com ordecação com a data manis antiga primeiro
    Page<Moto> findByDataEntradaOrderByDataEntradaAsc(LocalDate dataEntrada, Pageable pageable);
}
