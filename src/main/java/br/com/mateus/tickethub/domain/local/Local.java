package br.com.mateus.tickethub.domain.local;

import br.com.mateus.tickethub.exception.EntidadeEmConflitoException;
import br.com.mateus.tickethub.exception.EntidadeNaoEncontradaException;
import br.com.mateus.tickethub.exception.ValidacaoException;
import br.com.mateus.tickethub.infrastructure.shared.endereco.Endereco;

import java.util.*;

public class Local {

    private final UUID id;
    private String nome;
    private Endereco endereco;
    private final List<Setor> setores = new ArrayList<>();

    public Local(String nome, Endereco endereco) {
        validarNome(nome);

        this.id = UUID.randomUUID();
        this.nome = nome;
        this.endereco = Objects.requireNonNull(endereco, "O endereço é obrigatório.");
    }


    public void alterarNome(String novoNome) {
        validarNome(novoNome);
        if (this.nome.equals(novoNome)) return;
        this.nome = novoNome;
    }

    public void alterarEndereco(Endereco novoEndereco) {
        this.endereco = Objects.requireNonNull(novoEndereco, "O novo endereço não pode ser nulo.");
    }

    public void adicionarSetor(Setor novoSetor) {
        Objects.requireNonNull(novoSetor);

        if ( verificarSetorExistente(novoSetor) ) {
            throw new EntidadeEmConflitoException("Já existe um setor com o nome '" + novoSetor.getNome() + "' neste local.");
        }

        this.setores.add(novoSetor);
    }

    public boolean removerSetor(UUID idSetor) {
        return setores.removeIf(setor -> setor.getId().equals(idSetor));
    }

    public Setor buscarSetorPorId(UUID idSetor) {
        return setores.stream()
                .filter(s -> s.getId().equals(idSetor))
                .findFirst()
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Setor não encontrado."));
    }


    private void validarNome(String nome) {
        Objects.requireNonNull(nome, "O nome não pode ser nulo.");

        if (nome.trim().length() < 3) {
            throw new ValidacaoException("O nome do local deve ter pelo menos 3 letras.");
        }
    }

    private boolean verificarSetorExistente(Setor novoSetor) {
        return setores.stream()
                .anyMatch(setor -> setor.getNome().equalsIgnoreCase(novoSetor.getNome()));
    }


    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public List<Setor> getSetores() {
        return Collections.unmodifiableList(setores);
    }

    public int getCapacidadeTotal() {
        return setores.stream().mapToInt(Setor::getCapacidade).sum();
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Local local)) return false;

        return Objects.equals(getId(), local.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return """
                Local Detalhado:
                -----------------
                ID: %s
                Nome: %s
                Endereço: %s
                Setores: %s
                """.formatted(
                    this.id,
                    this.nome,
                    endereco,
                    this.setores
        );
    }
}
