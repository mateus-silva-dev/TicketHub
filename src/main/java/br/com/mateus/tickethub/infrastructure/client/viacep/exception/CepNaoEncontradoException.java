package br.com.mateus.tickethub.infrastructure.client.viacep.exception;

public class CepNaoEncontradoException extends RuntimeException {
    public CepNaoEncontradoException() {
    }

    public CepNaoEncontradoException(String message) {
        super(message);
    }
}
