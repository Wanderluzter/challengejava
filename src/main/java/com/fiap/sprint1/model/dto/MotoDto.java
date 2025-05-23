package com.fiap.sprint1.model.dto;

import java.time.LocalDate;

import com.fiap.sprint1.model.Tag;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MotoDto {

    private Long id;
    private String placa;
    @Size(min = 1, max = 100, message = "A observação deve ter entre 1 e 100 caracteres")
    private String observacoes;
    private LocalDate dataEntrada;
    private String fotos;
    private Tag tag;

}
