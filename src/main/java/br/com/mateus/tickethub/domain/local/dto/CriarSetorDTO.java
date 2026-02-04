package br.com.mateus.tickethub.domain.local.dto;

import java.math.BigDecimal;

public record CriarSetorDTO (String nome,
                             int capacidade,
                             BigDecimal precoBase)
{}
