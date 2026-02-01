package br.com.mateus.tickethub.service.notificacao;

import br.com.mateus.tickethub.model.Usuario;

public class EmailNotificador implements Notificador {
    @Override
    public void enviar(Usuario destinatario, String assunto, String mensagem) {
        System.out.println("--------------------------------------------------");
        System.out.println("ENVIANDO E-MAIL PARA: " + destinatario.getEmail());
        System.out.println("ASSUNTO: " + assunto);
        System.out.println("CORPO: " + mensagem);
        System.out.println("--------------------------------------------------");
    }

}
