package br.com.mateus.tickethub.domain.ingresso;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IngressoRepository {
    void salvar(Ingresso ingresso);
    Optional<Ingresso> buscarPorId(UUID ingressoId);
    List<Ingresso> buscarPorEvento(UUID eventoId);
    List<Ingresso> buscarPorSetor(UUID setorId);
    List<Ingresso> buscarPorComprador(UUID compradorId);
    List<Ingresso> buscarPorStatus(StatusIngresso status);
    long contarPorEventoSetorEStatus(UUID eventoId, UUID setorId, StatusIngresso status);
}
