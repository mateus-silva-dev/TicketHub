package br.com.mateus.tickethub.model;

import br.com.mateus.tickethub.model.enums.StatusEvento;
import br.com.mateus.tickethub.service.notificacao.Notificador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

public class Evento {

    private final UUID id;
    private String nome;
    private LocalDateTime dataRealizacao;
    private Local local;
    private String descricao;
    private StatusEvento status;

    public Evento(String nome, LocalDateTime dataRealizacao, Local local, String descricao) {
        validarNome(nome);
        validarDataRealizacao(dataRealizacao);
        validarDescricao(descricao);

        this.id = UUID.randomUUID();
        this.nome = nome;
        this.dataRealizacao = dataRealizacao;
        this.local = Objects.requireNonNull(local, "O local é obrigatório");
        this.descricao = descricao;
        this.status = StatusEvento.AGENDADO;
    }


    public void alterarNome(String novoNome) {
        validarNome(novoNome);
        this.nome = novoNome;
    }

    public void alterarDataRealizacao(LocalDateTime novoDataRealizacao) {
        validarDataRealizacao(novoDataRealizacao);
        this.dataRealizacao = novoDataRealizacao;
    }

    public void alterarLocal(Local novoLocal) {
        this.local = Objects.requireNonNull(novoLocal);
    }

    public void alterarDescricao(String novoDescricao) {
        validarDescricao(novoDescricao);
        this.descricao = novoDescricao;
    }

    public void abrirParaVendas() {
        this.status = Objects.requireNonNull(StatusEvento.ABERTO_PARA_VENDAS);
    }

    public void esgotar() {
        if (status != StatusEvento.ABERTO_PARA_VENDAS) {
            throw new IllegalStateException("Somente eventos abertos podem ser esgotados.");
        }
        this.status = StatusEvento.ESGOTADO;
    }

    public void finalizar() {
        if (status == StatusEvento.CANCELADO) {
            throw new IllegalStateException("Evento cancelado não pode ser finalizado.");
        }
        if (dataRealizacao.isAfter(LocalDateTime.now())) {
            throw new IllegalStateException("Evento só pode ser finalizado após a data.");
        }
        this.status = StatusEvento.FINALIZADO;
    }

    public void cancelar() {
        if (status == StatusEvento.FINALIZADO) {
            throw new IllegalStateException("Evento já finalizado não pode ser cancelado.");
        }
        this.status = StatusEvento.CANCELADO;
    }


    protected void validarNome(String nome) {
        Objects.requireNonNull(nome, "O nome não pode ser nulo.");

        if (nome.trim().length() < 3) {
            throw new IllegalArgumentException("O nome do evento deve ter pelo menos 3 letras.");
        }

        if (this.nome != null && this.nome.equals(nome)) {
            throw new IllegalArgumentException("O novo nome deve ser diferente do atual.");
        }
    }

    protected void validarDataRealizacao(LocalDateTime dataRealizacao) {
        Objects.requireNonNull(dataRealizacao, "A data não pode ser nula");

        if ( dataRealizacao.isBefore(LocalDateTime.now()) ) {
            throw new IllegalArgumentException("A data do evento deve ser uma data futura.");
        }

        if (this.dataRealizacao != null && dataRealizacao.isEqual(this.dataRealizacao)) {
            throw new IllegalArgumentException("A nova data deve ser diferente da atual.");
        }
    }

    protected void validarDescricao(String descricao) {
        Objects.requireNonNull(descricao, "A descrição não pode ser nula");

        if (descricao.trim().length() < 10) {
            throw new IllegalArgumentException("A descrição deve ser mais detalhada (mínimo 10 caracteres).");
        }
    }


    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDateTime getDataRealizacao() {
        return dataRealizacao;
    }

    public Local getLocal() {
        return local;
    }

    public String getDescricao() {
        return descricao;
    }

    public StatusEvento getStatus() {
        return status;
    }


    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Evento evento)) return false;

        return Objects.equals(getId(), evento.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return """
                Evento Detalhado:
                -----------------
                ID: %s
                Nome: %s
                Data Realização: %s
                Local: %s
                Status: %s
                """.formatted(
                    this.id,
                    this.nome,
                    this.dataRealizacao.format(formatter),
                    this.local.getNome(),
                    this.status
        );
    }
}
