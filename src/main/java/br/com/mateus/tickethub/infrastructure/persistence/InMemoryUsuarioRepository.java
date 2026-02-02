package br.com.mateus.tickethub.infrastructure.persistence;

import br.com.mateus.tickethub.domain.usuario.Usuario;
import br.com.mateus.tickethub.domain.usuario.UsuarioRepository;
import br.com.mateus.tickethub.domain.usuario.exception.UsuarioNaoEncontradoException;

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
}
