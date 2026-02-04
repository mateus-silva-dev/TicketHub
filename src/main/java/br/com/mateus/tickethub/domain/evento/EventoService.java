package br.com.mateus.tickethub.domain.evento;

import br.com.mateus.tickethub.domain.evento.dto.CriarEventoDTO;

public class EventoService {

    private final EventoRepository repository;

    public EventoService(EventoRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(CriarEventoDTO eventoDTO) {

    }

}
