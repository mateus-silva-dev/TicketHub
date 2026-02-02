package br.com.mateus.tickethub.repository.impl;

import br.com.mateus.tickethub.model.Evento;
import br.com.mateus.tickethub.model.enums.StatusEvento;
import br.com.mateus.tickethub.repository.EventoRepository;

import java.util.*;

public class InMemoryEventoRepository implements EventoRepository {

    private final List<Evento> eventos = new ArrayList<>();

    @Override
    public void salvar(Evento evento) {
        buscarPorId(evento.getId()).ifPresent(eventos::remove);
        eventos.add(evento);
    }

    @Override
    public Optional<Evento> buscarPorId(UUID id) {
        return eventos.stream()
                .filter(evento -> evento.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Evento> buscarPorNome(String nome) {
        return eventos.stream()
                .filter(evento -> evento.getNome().toLowerCase().contains(nome.toLowerCase()))
                .toList();
    }

    @Override
    public List<Evento> buscarTodos() {
        return Collections.unmodifiableList(eventos);
    }

    @Override
    public List<Evento> buscarPorStatus(StatusEvento statusEvento) {
        return eventos.stream()
                .filter(evento -> evento.getStatus() == statusEvento)
                .toList();
    }

    @Override
    public void deletar(UUID id) {
        eventos.removeIf(evento -> evento.getId().equals(id));
    }
}
