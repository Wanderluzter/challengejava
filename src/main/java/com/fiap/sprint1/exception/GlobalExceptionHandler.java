package com.fiap.sprint1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroResposta> handleRuntimeException(RuntimeException ex) {
        ErroResposta erroResposta = new ErroResposta("Erro interno", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(erroResposta);

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroResposta> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErroResposta erroResposta = new ErroResposta("Argumento inválido", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erroResposta);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErroResposta> handleNullPointerException(NullPointerException ex) {
        ErroResposta erroResposta = new ErroResposta("Erro de null pointer", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(erroResposta);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResposta> handleException(Exception ex) {
        ErroResposta erroResposta = new ErroResposta("Erro inesperado", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(erroResposta);
    }

    @ExceptionHandler(RecursoLigadoAOutroException.class)
    public ResponseEntity<ErroResposta> handleRecursoLigadoAOutro(RecursoLigadoAOutroException ex) {
        ErroResposta erro = new ErroResposta("Recurso em uso", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResposta> handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {
        ErroResposta erro = new ErroResposta("Não encontrado", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

}
