package com.fiap.sprint1.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.sprint1.model.Moto;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long> {

    Page<Moto> findByPlaca(String placa, Pageable pageable);
    Page<Moto> findByDataEntrada(Date dataEntrada, Pageable pageable);
}
