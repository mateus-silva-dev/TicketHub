package br.com.mateus.tickethub.domain.evento.dto;

import br.com.mateus.tickethub.domain.local.Local;

import java.time.LocalDateTime;

public record CriarEventoDTO (String nome,
                              LocalDateTime dataHora,
                              Local local,
                              String descricao) {
}
