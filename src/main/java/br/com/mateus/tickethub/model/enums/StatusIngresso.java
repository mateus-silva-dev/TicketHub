package br.com.mateus.tickethub.model.enums;

public enum StatusIngresso {
    DISPONIVEL,     // Pode ser comprado
    RESERVADO,      // Separado, aguardando pagamento
    VENDIDO,        // Pago
    UTILIZADO,      // Check-in realizado
    CANCELADO,      // Cancelado pelo usuário ou sistema
    EXPIRADO
}
