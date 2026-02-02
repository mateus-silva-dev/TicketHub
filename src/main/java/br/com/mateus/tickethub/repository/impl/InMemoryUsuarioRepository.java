package br.com.mateus.tickethub.repository.impl;

import br.com.mateus.tickethub.model.Usuario;
import br.com.mateus.tickethub.repository.UsuarioRepository;

import java.util.*;

public class InMemoryUsuarioRepository implements UsuarioRepository {

    private final List<Usuario> usuarios = new ArrayList<>();


    @Override
    public void salvar(Usuario usuario) {
        buscarPorId(usuario.getId()).ifPresent(usuarios::remove);
        usuarios.add(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorId(UUID id) {
        return usuarios.stream()
                .filter(usuario -> usuario.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarios.stream()
                .filter(usuario -> usuario.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public List<Usuario> buscarTodos() {
        return Collections.unmodifiableList(usuarios);
    }

    @Override
    public void deletar(UUID id) {
        usuarios.removeIf(usuario -> usuario.getId().equals(id));
    }

}
