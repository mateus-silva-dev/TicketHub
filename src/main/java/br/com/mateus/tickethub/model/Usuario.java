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
        validarNome(nome);
        validarEmail(email);
        validarSenha(senha);

        this.id = UUID.randomUUID();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.admin = admin;
    }


    public void alterarNome(String novoNome) {
        validarNome(novoNome);
        this.nome = novoNome;
    }

    public void alterarEmail(String novoEmail) {
        validarEmail(novoEmail);
        this.email = Objects.requireNonNull(novoEmail);
    }

    public void alterarSenha(String novaSenha) {
        validarSenha(novaSenha);
        this.senha = Objects.requireNonNull(novaSenha);
    }

    public void promoverAAdmin() {
        this.admin = true;
    }

    public void removerPrivilegiosAdmin() {
        this.admin = false;
    }


    protected void validarEmail (String email) {
        Objects.requireNonNull(email, "O email não pode ser nulo");

        if ( !email.matches("^[A-Za-z0-9._-]+@[a-z0-9.-]+\\.[a-z]{2,}$") ) {
            throw new IllegalArgumentException("Formato de email inválido.");
        }

        if ( email.equals(this.email) ) {
            throw new IllegalArgumentException("O novo email deve ser diferente do email atual.");
        }
    }

    protected void validarNome(String nome) {
        Objects.requireNonNull(nome, "O nome não pode ser nulo.");

        if (nome.trim().length() < 3) {
            throw new IllegalArgumentException("O nome de usuário deve ter pelo menos 3 letras.");
        }

        if (this.nome != null && this.nome.equals(nome)) {
            throw new IllegalArgumentException("O novo nome deve ser diferente do atual.");
        }
    }

    protected void validarSenha(String senha) {
        Objects.requireNonNull(senha, "A senha não pode ser nula.");

        if ( senha.length() < 6 ) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 6 caracteres.");
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
