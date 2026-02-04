package br.com.mateus.tickethub.domain.local.dto;

import java.util.List;

public record CriarLocalDTO(
        String nome,
        String cep,
        String numero,
        String complemento,
        List<CriarSetorDTO> setores
) {}
