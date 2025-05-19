package com.fiap.sprint1.model.dto;

import lombok.Data;

@Data
public class MotoDto {

    private Long id;
    private String placa;
    private String observacao;
    private String dataEntrada;
    private byte[] foto;
    private String idTag;
    
}
