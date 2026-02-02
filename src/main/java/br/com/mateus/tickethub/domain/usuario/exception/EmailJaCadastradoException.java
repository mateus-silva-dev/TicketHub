package br.com.mateus.tickethub.domain.usuario.exception;

import br.com.mateus.tickethub.exception.DomainException;

public class EmailJaCadastradoException extends DomainException {
    public EmailJaCadastradoException() {
        super(  "Já existe um usuário cadastrado com este email." );
    }
    public EmailJaCadastradoException(String email) {
        super(  "Já existe um usuário cadastrado com o e-mail: " + email );
    }
}
