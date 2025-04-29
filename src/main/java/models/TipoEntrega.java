package models;

public enum TipoEntrega {
	CARRO("Carro"), SEDEX("SEDEX"), RETIRADA("Retirada"), TRANSPORTADORA("Transportadora");

	private final String descricao;

	private TipoEntrega(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
