package br.com.mateus.tickethub.model;

import java.util.Objects;
import java.util.UUID;

public class Usuario {
    private final UUID id;
    private String nome;
    private String email;
    private String senha;
    private boolean admin;

    public Usuario(String nome, String email, String senha, boolean admin) {
        if ( nome == null || nome.isEmpty() ) {
            throw new IllegalArgumentException("Um nome deve ser informado.");
        }
        if ( email == null || email.isEmpty() ) {
            throw new IllegalArgumentException("Um email deve ser informado.");
        }
        if ( senha == null || senha.isEmpty() ) {
            throw new IllegalArgumentException("Uma senha deve ser informado.");
        }

        if ( !email.matches("^[A-Za-z0-9._-]+@[a-z0-9.-]+\\.[a-z]{2,}$") ) {
            throw new IllegalArgumentException("Formato de email inválido.");
        }

        this.id = UUID.randomUUID();
        this.nome = Objects.requireNonNull(nome);
        this.email = Objects.requireNonNull(email);
        this.senha = Objects.requireNonNull(senha);
        this.admin = false;
    }

    public void alterarNome(String novoNome) {
        if ( novoNome == null || novoNome.isEmpty() ) {
            throw new IllegalArgumentException("Um nome deve ser informado.");
        }
        this.nome = Objects.requireNonNull(novoNome);
    }

    public void alterarEmail(String novoEmail) {
        if (  novoEmail == null || novoEmail.isEmpty() ) {
            throw new IllegalArgumentException("Um email deve ser informado.");
        }

        if ( !novoEmail.matches("^[A-Za-z0-9._-]+@[a-z0-9.-]+\\.[a-z]{2,3}$") ) {
            throw new IllegalArgumentException("Formato de email inválido.");
        }

        if ( novoEmail.equals(this.email) ) {
            throw new IllegalArgumentException("O novo email deve ser diferente do email atual.");
        }

        this.email = Objects.requireNonNull(novoEmail);
    }

    public void alterarSenha(String novaSenha) {
        if ( novaSenha == null || novaSenha.isEmpty() ) {
            throw new IllegalArgumentException("Uma senha deve ser informado.");
        }

        this.senha = Objects.requireNonNull(novaSenha);
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
        return true;
    }
}
