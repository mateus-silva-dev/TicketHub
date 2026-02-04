package br.com.mateus.tickethub.domain.usuario;

import br.com.mateus.tickethub.domain.usuario.dto.AtualizarUsuarioDTO;
import br.com.mateus.tickethub.domain.usuario.dto.CriarUsuarioDTO;
import br.com.mateus.tickethub.exception.EntidadeNaoEncontradaException;
import br.com.mateus.tickethub.exception.DomainException;

import java.util.List;
import java.util.UUID;

public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }


    public Usuario cadastrar(CriarUsuarioDTO usuarioDTO) {
        validarUnicidadeEmail(usuarioDTO.email());
        Usuario usuario = new Usuario(usuarioDTO.nome(), usuarioDTO.email(), usuarioDTO.senha(), false);
        repository.salvar(usuario);
        return usuario;
    }

    public void atualizar(UUID id, AtualizarUsuarioDTO usuarioDTO) {
        Usuario usuario = validarUsuarioExistente(id);

        if ( usuarioDTO.nome() != null
                && !usuarioDTO.nome().isBlank() ) {
            usuario.alterarNome(usuarioDTO.nome());
        }
        if ( usuarioDTO.senha() != null
                && !usuarioDTO.senha().isBlank() ) {
            usuario.alterarSenha(usuarioDTO.senha());
        }
        if ( usuarioDTO.email() != null
                && !usuarioDTO.email().isBlank()
                && !usuarioDTO.email().equals(usuario.getEmail()) ) {
            validarUnicidadeEmail(usuarioDTO.email());
            usuario.alterarEmail(usuarioDTO.email());
        }

        repository.salvar(usuario);
    }

    public void tornarAdmin(UUID idDoUsuarioAlvo) {
        Usuario usuario = validarUsuarioExistente(idDoUsuarioAlvo);
        usuario.promoverAAdmin();
        repository.salvar(usuario);
    }

    public void revogarAdmin(UUID idDoUsuarioAlvo) {
        Usuario usuario = validarUsuarioExistente(idDoUsuarioAlvo);
        usuario.revogarAdmin();
        repository.salvar(usuario);
    }

    public void desativarUsuario(UUID idDoUsuarioAlvo) {
        Usuario usuario = validarUsuarioExistente(idDoUsuarioAlvo);
        usuario.desativarUsuario();
        repository.salvar(usuario);
    }

    public List<Usuario> listarTodosUsuarios(UUID idUsuarioSolicitante) {
        Usuario usuario = validarUsuarioExistente(idUsuarioSolicitante);

        if ( !usuario.isAdmin() ) {
            throw new DomainException("Apenas administradores podem listar usuários.");
        }

        return repository.buscarTodos();
    }


    private void validarUnicidadeEmail(String email) {
        repository.buscarPorEmail(email).ifPresent(usuario -> {
            throw new DomainException("O e-mail informado já está em uso.");
        });
    }

    private Usuario validarUsuarioExistente(UUID id) {
        return repository.buscarPorId(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado."));
    }
}
