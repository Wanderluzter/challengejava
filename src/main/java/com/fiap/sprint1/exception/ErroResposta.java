package com.fiap.sprint1.exception;

import lombok.Data;

@Data
public class ErroResposta {
    private String erro;
    private String detalhe;

    public ErroResposta(String erro, String detalhe) {
        this.erro = erro;
        this.detalhe = detalhe;
    }

}
