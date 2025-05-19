package com.fiap.sprint1.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiap.sprint1.model.Moto;
import com.fiap.sprint1.repository.MotoRepository;

@Service
public class MotoService {

    @Autowired
    private MotoRepository motoRepository;

    public Optional<Moto> saveMoto(Moto moto) {
        Moto savedMoto = motoRepository.save(moto);
        return Optional.ofNullable(savedMoto);
    }

    public void deleteMoto(Long id) {
        motoRepository.deleteById(id);
    }

    public Moto getMotoById(Long id) {
        return motoRepository.findById(id).orElse(null);
    }

    public Page<Moto> getAllMotos(Pageable pageable) {
        return motoRepository.findAll(pageable);
    }

    public Moto updateMoto(Moto moto) {
        return motoRepository.save(moto);
    }

    public Page<Moto> getMotosByPlaca(String placa, Pageable pageable) {
        return motoRepository.findByPlaca(placa, pageable);
    }

    public Page<Moto> getMotosByDataEntrada(Date dataEntrada, Pageable pageable) {
        return motoRepository.findByDataEntrada(dataEntrada, pageable);
    }
}
