package br.com.mateus.tickethub.model;

import br.com.mateus.tickethub.model.enums.StatusEvento;
import br.com.mateus.tickethub.model.enums.StatusIngresso;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Ingresso {

    private final UUID id;
    private final Evento evento;
    private final Setor setor;
    private final Usuario comprador;

    private BigDecimal valorPago;
    private StatusIngresso status;

    public Ingresso(Evento evento, Setor setor, Usuario comprador) {
        this.evento = Objects.requireNonNull(evento, "O evento não pode ser nulo.");
        this.setor = Objects.requireNonNull(setor, "Deve ser um setor existente.");
        this.comprador = Objects.requireNonNull(comprador, "Deve ser um comprador existente.");

        this.id = UUID.randomUUID();
        this.status = StatusIngresso.DISPONIVEL;
    }


    public void reservar() {
        if (evento.getStatus() != StatusEvento.ABERTO_PARA_VENDAS) {
            throw new IllegalStateException("Evento não está aberto para vendas.");
        }

        if (status != StatusIngresso.DISPONIVEL) {
            throw new IllegalStateException("Ingresso não disponível para reserva.");
        }

        this.status = StatusIngresso.RESERVADO;
    }

    public void confirmarPagamento(BigDecimal valorPago) {
        if (status != StatusIngresso.RESERVADO) {
            throw new IllegalStateException("Ingresso não está reservado.");
        }

        Objects.requireNonNull(valorPago, "O valor pago não pode ser nulo.");

        this.valorPago = valorPago;
        this.status = StatusIngresso.VENDIDO;
    }

    public void expirar() {
        if (status != StatusIngresso.RESERVADO) {
            throw new IllegalStateException("Somente ingressos reservados podem expirar.");
        }

        this.status = StatusIngresso.EXPIRADO;
    }

    public void utilizar() {
        if (status != StatusIngresso.VENDIDO) {
            throw new IllegalStateException("Somente ingressos vendidos podem ser utilizados.");
        }

        this.status = StatusIngresso.UTILIZADO;
    }

    public void cancelar() {
        if (status == StatusIngresso.UTILIZADO) {
            throw new IllegalStateException("Ingresso já utilizado não pode ser cancelado.");
        }

        this.status = StatusIngresso.CANCELADO;
    }


    public UUID getId() {
        return id;
    }

    public Evento getEvento() {
        return evento;
    }

    public Setor getSetor() {
        return setor;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public StatusIngresso getStatus() {
        return status;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Ingresso ingresso)) return false;

        return getId().equals(ingresso.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return """
           -------------------------------
           INGRESSO: %s
           EVENTO: %s
           SETOR: %s
           COMPRADOR: %s
           VALOR PAGO: R$ %.2f
           STATUS: %s
           -------------------------------
           """.formatted(
                id,
                evento.getNome(),
                setor.getNome(),
                comprador.getNome(),
                valorPago != null ? valorPago : BigDecimal.ZERO,
                status
        );
    }
}
