package br.com.mateus.tickethub.infrastructure.shared.endereco.dto;

public record CriarEnderecoDTO(String cep,
                               String logradouro,
                               String complemento,
                               String bairro,
                               String localidade,
                               String uf)
{

    @Override
    public String toString() {
        return """
               %s
               %s, %s
               %s, %s - %s
               """.formatted(
                this.cep,
                this.logradouro,
                this.complemento,
                this.bairro,
                this.localidade,
                this.uf);
    }

}
