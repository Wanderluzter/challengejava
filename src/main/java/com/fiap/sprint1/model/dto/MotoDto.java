package com.fiap.sprint1.model.dto;

import java.util.Date;

import com.fiap.sprint1.model.Tag;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MotoDto {

    @NotNull(message = "ID não pode ser vazio")
    private Long id;
    private String placa;
    @Size(min = 1, max = 100, message = "A observação deve ter entre 1 e 100 caracteres")
    private String observacao;
    private Date dataEntrada;
    private byte[] foto;
    private Tag tag;

}
