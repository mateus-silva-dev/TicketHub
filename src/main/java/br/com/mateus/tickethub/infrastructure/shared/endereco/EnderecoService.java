package br.com.mateus.tickethub.infrastructure.shared.endereco;

import br.com.mateus.tickethub.infrastructure.client.viacep.ViaCepClient;
import com.google.gson.Gson;

public class EnderecoService {

    public Endereco buscarPorCep(String cep) {
        String json = ViaCepClient.buscarEnderecoJson(cep);

        if ( json.contains("\"erro\": true") ) {
            throw new IllegalArgumentException("CEP inválido");
        }

        return new Gson().fromJson(json, Endereco.class);
    }

}
