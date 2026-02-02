package br.com.mateus.tickethub.infrastructure.persistence;

import br.com.mateus.tickethub.domain.local.Setor;
import br.com.mateus.tickethub.domain.local.SetorRepository;

import java.util.*;

public class InMemorySetorRepository implements SetorRepository {

    private final List<Setor> setores = new ArrayList<>();

    @Override
    public void salvar(Setor setor) {
        buscarPorId(setor.getId()).ifPresent(setores::remove);
        setores.add(setor);
    }

    @Override
    public Optional<Setor> buscarPorId(UUID id) {
        return setores.stream()
                .filter(setor -> setor.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Setor> buscarPorNome(String nome) {
        return setores.stream()
                .filter(setor -> setor.getNome().toLowerCase().contains(nome.toLowerCase()))
                .toList();
    }

    @Override
    public List<Setor> buscarTodos() {
        return Collections.unmodifiableList(setores);
    }

    @Override
    public void deletar(UUID id) {
        setores.removeIf(setor -> setor.getId().equals(id));
    }
}
