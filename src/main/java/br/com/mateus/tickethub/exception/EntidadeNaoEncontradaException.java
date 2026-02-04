package br.com.mateus.tickethub.exception;

import java.util.UUID;

public class EntidadeNaoEncontradaException extends DomainException {

    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }

}
