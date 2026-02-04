package br.com.mateus.tickethub.domain.local;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LocalRepository {
    void cadastrar(Local local);
    Optional<Local> buscarPorId(UUID id);
    List<Local> buscarPorNome(String nome);
    List<Local> buscarTodos();
    void deletar(UUID id);
}
