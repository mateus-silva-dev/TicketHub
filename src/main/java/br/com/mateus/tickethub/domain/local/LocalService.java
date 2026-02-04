package br.com.mateus.tickethub.domain.local;

import br.com.mateus.tickethub.domain.local.dto.CriarLocalDTO;
import br.com.mateus.tickethub.infrastructure.shared.endereco.Endereco;
import br.com.mateus.tickethub.infrastructure.shared.endereco.EnderecoService;

import java.util.List;

public class LocalService {

    private final LocalRepository repository;
    private final EnderecoService enderecoService;

    public LocalService(LocalRepository repository, EnderecoService enderecoService) {
        this.repository = repository;
        this.enderecoService = enderecoService;
    }

    public void cadastrar(CriarLocalDTO localDTO) {
        Endereco endereco = enderecoService.criarPorCep(localDTO.cep(), localDTO.numero(), localDTO.complemento());

        Local local = new Local(localDTO.nome(), endereco);

        if (localDTO.setores() != null) {
            localDTO.setores().forEach(s -> {
                Setor novoSetor = new Setor(s.nome(), s.capacidade(), s.precoBase());
                local.adicionarSetor(novoSetor);
            });
        }

        repository.cadastrar(local);
    }


}
