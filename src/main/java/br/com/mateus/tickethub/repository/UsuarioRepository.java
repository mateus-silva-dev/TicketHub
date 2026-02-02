package br.com.mateus.tickethub.repository;

import br.com.mateus.tickethub.model.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository {
    void salvar(Usuario usuario);
    Optional<Usuario> buscarPorId(UUID id);
    Optional<Usuario> buscarPorEmail(String email);
    List<Usuario> buscarTodos();
    void deletar(UUID id);
}
