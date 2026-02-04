package br.com.mateus.tickethub.infrastructure.shared.endereco;

import br.com.mateus.tickethub.infrastructure.client.viacep.ViaCepClient;
import br.com.mateus.tickethub.infrastructure.shared.endereco.dto.CriarEnderecoDTO;
import com.google.gson.Gson;

public class EnderecoService {

    public Endereco buscarPorCep(String cep) {
        String json = ViaCepClient.buscarEnderecoJson(cep);

        if ( json.contains("\"erro\": true") ) {
            throw new IllegalArgumentException("CEP inválido");
        }

        return new Gson().fromJson(json, Endereco.class);
    }

    public Endereco criarPorCep(String cep, String numero, String complemento) {
        var response = buscarPorCep(cep);

        return new Endereco (
                response.getCep(),
                response.getLogradouro(),
                numero,
                complemento,
                response.getBairro(),
                response.getLocalidade(),
                response.getUf()
        );
    }

}
