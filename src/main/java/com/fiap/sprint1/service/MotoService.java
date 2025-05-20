package com.fiap.sprint1.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiap.sprint1.model.Moto;
import com.fiap.sprint1.model.dto.MotoDto;
import com.fiap.sprint1.repository.MotoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MotoService {

    @Autowired
    private MotoRepository motoRepository;

    public Optional<MotoDto> saveMoto(MotoDto dto) {
        Moto savedMoto = toEntity(dto);
        return Optional.ofNullable(savedMoto).map(motoRepository::save)
                .map(this::toDto);
    }

    public void deleteMoto(Long id) {
        motoRepository.deleteById(id);
    }

    public MotoDto getMotoById(Long id) {
        return motoRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    public Page<MotoDto> getAllMotos(Pageable pageable) {
        return motoRepository.findAll(pageable).map(this::toDto);
    }

    public MotoDto updateMoto(MotoDto dto) {
        Moto moto = motoRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Moto não encontrada"));
        moto.setPlaca(dto.getPlaca());
        moto.setObservacao(dto.getObservacao());
        moto.setDataEntrada(dto.getDataEntrada());
        moto.setFoto(dto.getFoto());
        moto.setTag(dto.getTag());
        Moto updatedMoto = motoRepository.save(moto);
        return toDto(updatedMoto);
    }

    public Page<MotoDto> getMotosByPlaca(String placa, Pageable pageable) {
        return motoRepository.findByPlaca(placa, pageable).map(this::toDto);
    }

    public Page<MotoDto> getMotosByDataEntrada(LocalDate dataEntrada, Pageable pageable) {
        return motoRepository.findByDataEntradaOrderByDataEntradaAsc(dataEntrada, pageable).map(this::toDto);
    }

    public Moto toEntity(MotoDto dto) {
        Moto moto = new Moto();
        moto.setId(dto.getId());
        moto.setPlaca(dto.getPlaca());
        moto.setObservacao(dto.getObservacao());
        moto.setDataEntrada(dto.getDataEntrada());
        moto.setFoto(dto.getFoto());
        moto.setTag(dto.getTag());
        return moto;
    }

    public MotoDto toDto(Moto moto) {
        MotoDto dto = new MotoDto();
        dto.setId(moto.getId());
        dto.setPlaca(moto.getPlaca());
        dto.setObservacao(moto.getObservacao());
        dto.setDataEntrada(moto.getDataEntrada());
        dto.setFoto(moto.getFoto());
        dto.setTag(moto.getTag());
        return dto;
    }

}
