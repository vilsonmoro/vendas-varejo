package models;

public enum FormaPagamento {
	DINHEIRO("Dinheiro"),
    PIX("Pix"),
    CREDITO("Crédito"),
    DEBITO("Débito"),
    TED("TED");

    private final String descricao;

    FormaPagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
