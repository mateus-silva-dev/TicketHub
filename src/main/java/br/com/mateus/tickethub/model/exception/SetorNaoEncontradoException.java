package br.com.mateus.tickethub.model.exception;

public class SetorNaoEncontradoException extends RuntimeException {
    public SetorNaoEncontradoException() {
    }
    public SetorNaoEncontradoException(String message) {
        super(message);
    }
}
