package com.fiap.sprint1.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiap.sprint1.exception.RecursoNaoEncontradoException;
import com.fiap.sprint1.model.Moto;
import com.fiap.sprint1.model.Tag;
import com.fiap.sprint1.model.dto.MotoDto;
import com.fiap.sprint1.repository.MotoRepository;
import com.fiap.sprint1.repository.TagRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MotoService {

    @Autowired
    private MotoRepository motoRepository;

    public Optional<MotoDto> saveMoto(MotoDto dto) {
        Moto moto = toEntity(dto);
        moto.setId(null);

        Tag tag = tagRepository.findById(dto.getTag().getId())
                .orElseThrow(() -> new EntityNotFoundException("Tag não encontrada"));
        moto.setTag(tag);

        Moto savedMoto = motoRepository.save(moto);
        return Optional.of(toDto(savedMoto));
    }

    @CacheEvict(value = "moto", key = "#id")
    public void deleteMoto(Long id) {
        motoRepository.deleteById(id);
    }

    @Cacheable(value = "moto", key = "#id")
    public MotoDto getMotoById(Long id) {
        return motoRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    public Page<MotoDto> getAllMotos(Pageable pageable) {
        return motoRepository.findAll(pageable).map(this::toDto);
    }

    @CachePut(value = "moto", key = "#dto.id")
    public MotoDto updateMoto(MotoDto dto) {
        Moto moto = motoRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Moto não encontrada"));
        moto.setPlaca(dto.getPlaca());
        moto.setObservacoes(dto.getObservacoes());
        moto.setDataEntrada(dto.getDataEntrada());
        moto.setFotos(dto.getFotos());
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

    @Autowired
    private TagRepository tagRepository;

    public Moto toEntity(MotoDto dto) {
        Moto moto = new Moto();
        moto.setId(dto.getId());
        moto.setPlaca(dto.getPlaca());
        moto.setObservacoes(dto.getObservacoes());
        moto.setDataEntrada(dto.getDataEntrada());
        moto.setFotos(dto.getFotos());

        // Buscar a tag pelo ID
        if (dto.getTag() != null && dto.getTag().getId() != null) {
            Tag tag = tagRepository.findById(dto.getTag().getId())
                    .orElseThrow(
                            () -> new RecursoNaoEncontradoException("Tag " + dto.getTag().getId() + " não encontrada"));
            moto.setTag(tag);
        } else {
            throw new IllegalArgumentException("A tag é obrigatória");
        }

        return moto;
    }

    public MotoDto toDto(Moto moto) {
        MotoDto dto = new MotoDto();
        dto.setId(moto.getId());
        dto.setPlaca(moto.getPlaca());
        dto.setObservacoes(moto.getObservacoes());
        dto.setDataEntrada(moto.getDataEntrada());
        dto.setFotos(moto.getFotos());
        dto.setTag(moto.getTag());
        return dto;
    }

}
