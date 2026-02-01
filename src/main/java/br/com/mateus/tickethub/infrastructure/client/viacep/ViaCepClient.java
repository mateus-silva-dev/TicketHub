package br.com.mateus.tickethub.infrastructure.client.viacep;

import br.com.mateus.tickethub.infrastructure.client.viacep.exception.CepNaoEncontradoException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ViaCepClient {

    private static final HttpClient client = HttpClient.newHttpClient();

    public static String buscarEnderecoJson(String cep) {
        try {
            URI uri = URI.create("https://viacep.com.br/ws/" + cep + "/json");

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();

        } catch (CepNaoEncontradoException e) {
            throw new RuntimeException("Erro ao buscar CEP", e);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
