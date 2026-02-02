package br.com.mateus.tickethub.repository;

import br.com.mateus.tickethub.model.Evento;
import br.com.mateus.tickethub.model.enums.StatusEvento;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventoRepository {
    void salvar(Evento evento);
    Optional<Evento> buscarPorId(UUID id);
    List<Evento> buscarPorNome(String nome);
    List<Evento> buscarPorStatus(StatusEvento statusEvento);
    List<Evento> buscarTodos();
    void deletar(UUID id);
}
