package br.com.mateus.tickethub.infrastructure.persistence;

import br.com.mateus.tickethub.domain.local.Local;
import br.com.mateus.tickethub.domain.local.LocalRepository;

import java.util.*;

public class InMemoryLocalRepository implements LocalRepository {

    private final List<Local> locais = new ArrayList<>();

    @Override
    public void salvar(Local local) {
        buscarPorId(local.getId()).ifPresent(locais::remove);
        locais.add(local);
    }

    @Override
    public Optional<Local> buscarPorId(UUID id) {
        return locais.stream()
                .filter(local -> local.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Local> buscarPorNome(String nome) {
        return locais.stream()
                .filter(local -> local.getNome().toLowerCase().contains(nome.toLowerCase()))
                .toList();
    }

    @Override
    public List<Local> buscarTodos() {
        return Collections.unmodifiableList(locais);
    }

    @Override
    public void deletar(UUID id) {
        locais.removeIf(local -> local.getId().equals(id));
    }
}
