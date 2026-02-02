package br.com.mateus.tickethub.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Setor {

    private final UUID id;
    private String nome;
    private int capacidade;
    private BigDecimal precoBase;

    public Setor(String nome, int capacidade, BigDecimal precoBase) {
        validarNome(nome);
        validarCapacidade(capacidade);
        validarPrecoBase(precoBase);

        this.id = UUID.randomUUID();
        this.nome = nome;
        this.capacidade = capacidade;
        this.precoBase = precoBase;
    }


    public void alterarNome(String novoNome) {
        validarNome(novoNome);
        this.nome = novoNome;
    }

    public void alterarCapacidade(int novaCapacidade) {
        validarCapacidade(novaCapacidade);
        this.capacidade = novaCapacidade;
    }

    public void alterarPrecoBase(BigDecimal novoPrecoBase) {
        validarPrecoBase(novoPrecoBase);
        this.precoBase = novoPrecoBase;
    }


    protected void validarNome(String nome) {
        Objects.requireNonNull(nome, "O nome não pode ser nulo.");

        if ( nome.trim().length() < 3 ) {
            throw new IllegalArgumentException("O nome do local deve ter pelo menos 3 letras.");
        }

        if ( this.nome != null && this.nome.equals(nome) ) {
            throw new IllegalArgumentException("O novo nome deve ser diferente do atual.");
        }
    }

    protected void validarCapacidade(int capacidade) {
        if ( capacidade <= 0 ) {
            throw new IllegalArgumentException("O capacidade deve ser maior que 1.");
        }

        if (this.capacidade > 0 && this.capacidade == capacidade) {
            throw new IllegalArgumentException("A nova capacidade deve ser diferente da atual.");
        }
    }

    protected void validarPrecoBase(BigDecimal precoBase) {
        Objects.requireNonNull(precoBase, "O preço base não pode ser nulo.");

        if (precoBase.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero.");
        }

        if (this.precoBase != null && this.precoBase.compareTo(precoBase) == 0) {
            throw new IllegalArgumentException("O novo preço base deve ser diferente do preço atual.");
        }
    }


    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public BigDecimal getPrecoBase() {
        return precoBase;
    }

    public boolean temCapacidade(long quantidadeVendida) {
        return quantidadeVendida < this.capacidade;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Setor setor)) return false;

        return Objects.equals(getId(), setor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return """
                ID: %s
                Setor: %s
                Capacidade: %d pessoas
                Preço Base: R$ %.2f
                """.formatted(
                        this.id,
                        this.nome,
                        this.capacidade,
                        this.precoBase
        );
    }

}
