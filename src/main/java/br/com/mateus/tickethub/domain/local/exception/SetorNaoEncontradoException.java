package br.com.mateus.tickethub.domain.local.exception;

import br.com.mateus.tickethub.exception.DomainException;

import java.util.UUID;

public class SetorNaoEncontradoException extends DomainException {
    public SetorNaoEncontradoException(UUID setorId) {
        super("Não foi possível encontrar o setor com o ID: " + setorId);
    }
}
