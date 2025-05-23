package com.fiap.sprint1.exception;

public class RecursoLigadoAOutroException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RecursoLigadoAOutroException(String mensagem) {
        super(mensagem);
    }

    public RecursoLigadoAOutroException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

}
