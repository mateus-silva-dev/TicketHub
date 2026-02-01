package br.com.mateus.tickethub.service.notificacao;

import br.com.mateus.tickethub.model.Usuario;

public interface Notificador {
    void enviar(Usuario destinatario, String assunto, String mensagem);
}
