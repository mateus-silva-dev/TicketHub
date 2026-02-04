package br.com.mateus.tickethub.domain.usuario;

import br.com.mateus.tickethub.exception.DomainException;
import br.com.mateus.tickethub.exception.ValidacaoException;

import java.util.Objects;
import java.util.UUID;

public class Usuario {

    private final UUID id;
    private String nome;
    private String email;
    private String senha;
    private boolean admin;
    private boolean ativo;


    public Usuario(String nome, String email, String senha, boolean admin) {
        validarNome(nome);
        validarEmail(email);
        validarSenha(senha);

        this.id = UUID.randomUUID();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.admin = admin;
        this.ativo = true;
    }


    public void alterarNome(String novoNome) {
        validarNome(novoNome);
        if (this.nome.equals(novoNome)) return;
        this.nome = novoNome;
    }

    public void alterarEmail(String novoEmail) {
        validarEmail(novoEmail);
        if (this.email.equals(novoEmail)) return;
        this.email = Objects.requireNonNull(novoEmail);
    }

    public void alterarSenha(String novaSenha) {
        validarSenha(novaSenha);
        this.senha = Objects.requireNonNull(novaSenha);
    }

    public void promoverAAdmin() {
        this.admin = true;
    }

    public void revogarAdmin() {
        this.admin = false;
    }

    public void desativarUsuario() {
        if (!this.ativo) {
            throw new DomainException("Usuário já está desativado.");
        }
        this.ativo = false;
    }


    private void validarNome(String nome) {
        Objects.requireNonNull(nome, "O nome não pode ser nulo.");

        if (nome.trim().length() < 3) {
            throw new ValidacaoException("O nome de usuário deve ter pelo menos 3 letras.");
        }
    }

    private void validarEmail (String email) {
        Objects.requireNonNull(email, "O email não pode ser nulo");

        if ( !email.matches("^[A-Za-z0-9._-]+@[a-z0-9.-]+\\.[a-z]{2,}$") ) {
            throw new ValidacaoException("Formato de email inválido.");
        }
    }

    private void validarSenha(String senha) {
        Objects.requireNonNull(senha, "A senha não pode ser nula.");

        if ( senha.length() < 6 ) {
            throw new ValidacaoException("A senha deve ter pelo menos 6 caracteres.");
        }
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public boolean isInativo() {
        return !isAtivo();
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Usuario usuario)) return false;

        return Objects.equals(getId(), usuario.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
