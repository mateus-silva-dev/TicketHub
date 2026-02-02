package br.com.mateus.tickethub.domain.usuario.exception;

import br.com.mateus.tickethub.exception.DomainException;

public class UsuarioNaoEncontradoException extends DomainException {

    public UsuarioNaoEncontradoException() {
        super("Usuário não encontrado.");
    }

}
