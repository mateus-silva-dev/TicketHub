package br.com.mateus.tickethub.repository.impl;

import br.com.mateus.tickethub.model.Ingresso;
import br.com.mateus.tickethub.model.enums.StatusIngresso;
import br.com.mateus.tickethub.repository.IngressoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InMemoryIngressoRepository implements IngressoRepository {

    private final List<Ingresso> ingressos = new ArrayList<>();

    @Override
    public void salvar(Ingresso ingresso) {
        buscarPorId(ingresso.getId()).ifPresent(ingressos::remove);
        ingressos.add(ingresso);
    }

    @Override
    public Optional<Ingresso> buscarPorId(UUID ingressoId) {
        return ingressos.stream()
                .filter(ingresso -> ingresso.getId().equals(ingressoId))
                .findFirst();
    }

    @Override
    public List<Ingresso> buscarPorEvento(UUID eventoId) {
        return ingressos.stream()
                .filter(evento -> evento.getId().equals(eventoId))
                .toList();
    }

    @Override
    public List<Ingresso> buscarPorSetor(UUID setorId) {
        return ingressos.stream()
                .filter(setor -> setor.getId().equals(setorId))
                .toList();
    }

    @Override
    public List<Ingresso> buscarPorComprador(UUID compradorId) {
        return ingressos.stream()
                .filter(comprador -> comprador.getId().equals(compradorId))
                .toList();
    }

    @Override
    public List<Ingresso> buscarPorStatus(StatusIngresso status) {
        return ingressos.stream()
                .filter(ingresso -> ingresso.getStatus() == status)
                .toList();
    }

    @Override
    public long contarPorEventoSetorEStatus(UUID eventoId, UUID setorId, StatusIngresso statusIngresso) {
        return ingressos.stream()
                .filter(id -> id.getEvento().getId().equals(eventoId))
                .filter(id -> id.getSetor().getId().equals(setorId))
                .filter(status -> status.getStatus() == statusIngresso)
                .count();
    }
}
