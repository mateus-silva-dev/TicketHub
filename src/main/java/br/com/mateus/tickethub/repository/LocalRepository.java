package br.com.mateus.tickethub.repository;

import br.com.mateus.tickethub.model.Local;
import br.com.mateus.tickethub.model.enums.StatusEvento;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LocalRepository {
    void salvar(Local local);
    Optional<Local> buscarPorId(UUID id);
    List<Local> buscarPorNome(String nome);
    List<Local> buscarTodos();
    void deletar(UUID id);
}
