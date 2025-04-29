package com.tcc2.ellemVeigaOficial.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FormaPagamento {
    DINHEIRO("Dinheiro"),
    PIX("Pix"),
    CREDITO("Cartão de Crédito"),
    DEBITO("Cartão de Débito"),
    TED("TED/DOC");

    private final String descricao;
}
