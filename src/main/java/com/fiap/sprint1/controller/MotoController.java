package com.fiap.sprint1.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.sprint1.exception.RecursoNaoEncontradoException;
import com.fiap.sprint1.model.dto.MotoDto;
import com.fiap.sprint1.service.MotoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/motos")
public class MotoController {

    @Autowired
    private MotoService motoService;

    // CRUD
    @GetMapping
    public ResponseEntity<Page<MotoDto>> getMotos(Pageable pageable) {
        Page<MotoDto> page = motoService.getAllMotos(pageable);
        if (page.isEmpty()) {
            throw new RecursoNaoEncontradoException("Nenhuma moto encontrada.");
        }
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotoDto> getMotoById(@PathVariable Long id) {
        Optional<MotoDto> motoDto = Optional.ofNullable(motoService.getMotoById(id));
        return motoDto.map(ResponseEntity::ok)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Moto " + id + " não encontrada"));
    }

    @PostMapping
    public ResponseEntity<MotoDto> postMoto(@Valid @RequestBody MotoDto dto) {
        Optional<MotoDto> motoDto = motoService.saveMoto(dto);
        return motoDto.map(savedMoto -> ResponseEntity.created(URI.create("/motos/" + savedMoto.getId()))
                .body(savedMoto)).orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MotoDto> putMoto(@PathVariable Long id, @Valid @RequestBody MotoDto dto) {
        dto.setId(id);
        MotoDto updatedMoto = motoService.updateMoto(dto);

        if (updatedMoto == null) {
            throw new RecursoNaoEncontradoException("Moto " + id + " não encontrada");
        }

        return ResponseEntity.ok(updatedMoto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMoto(@PathVariable Long id) {
        Optional<MotoDto> motoDto = Optional.ofNullable(motoService.getMotoById(id));
        if (motoDto.isEmpty()) {
            throw new RecursoNaoEncontradoException("Moto " + id + " não encontrada");
        }
        motoService.deleteMoto(id);
        return ResponseEntity.noContent().build();
    }

    // Busca por parâmetros (Com páginação e ordenação)
    @GetMapping("/data/{dataEntrada}")
    public ResponseEntity<Page<MotoDto>> getMotosByDataEntrada(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataEntrada, Pageable pageable) {

        Page<MotoDto> page = motoService.getMotosByDataEntrada(dataEntrada, pageable);

        if (page.isEmpty()) {
            throw new RecursoNaoEncontradoException(
                    "Nenhuma moto encontrada com a data de entrada a partir de " + dataEntrada);
        }
        return ResponseEntity.ok(page);
    }

}