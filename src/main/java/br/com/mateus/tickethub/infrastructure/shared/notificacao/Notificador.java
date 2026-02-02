package br.com.mateus.tickethub.infrastructure.shared.notificacao;

import br.com.mateus.tickethub.domain.usuario.Usuario;

public interface Notificador {
    void enviar(Usuario destinatario, String assunto, String mensagem);
}
