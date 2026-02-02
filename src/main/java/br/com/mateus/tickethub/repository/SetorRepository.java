package br.com.mateus.tickethub.repository;

import br.com.mateus.tickethub.model.Setor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SetorRepository {
    void salvar(Setor setor);
    Optional<Setor> buscarPorId(UUID id);
    List<Setor> buscarPorNome(String nome);
    List<Setor> buscarTodos();
    void deletar(UUID id);
}
