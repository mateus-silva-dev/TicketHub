package br.com.mateus.tickethub.infrastructure.shared.endereco;

import java.util.Objects;

public class Endereco {

    private final String cep;
    private final String logradouro;
    private final String numero;
    private final String complemento;
    private final String bairro;
    private final String localidade;
    private final String uf;

    public Endereco(String cep, String logradouro, String numero, String complemento, String bairro, String localidade, String uf) {
        this.cep = Objects.requireNonNull(cep);
        this.logradouro = logradouro;
        this.numero = Objects.requireNonNull(numero, "O número é obrigatório (use S/N se não houver).");
        this.complemento = (complemento == null) ? "" : complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getUf() {
        return uf;
    }

    @Override
    public String toString() {
        return """
               %s
               %s, %s - %s
               %s, %s - %s
               """.formatted(
                this.cep,
                this.logradouro,
                this.numero,
                complemento.isBlank() ? "" : this.complemento,
                this.bairro,
                this.localidade,
                this.uf);
    }
}
