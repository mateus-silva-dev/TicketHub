package br.com.mateus.tickethub.domain.ingresso;

public enum StatusIngresso {
    DISPONIVEL,     // Pode ser comprado
    RESERVADO,      // Separado, aguardando pagamento
    VENDIDO,        // Pago
    UTILIZADO,      // Check-in realizado
    CANCELADO,      // Cancelado pelo usuário ou sistema
    EXPIRADO
}
