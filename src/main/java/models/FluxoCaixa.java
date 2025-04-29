package models;

import java.time.LocalDate;

public class FluxoCaixa {
    private LocalDate data;
    private Long idProduto;
    private Long idVenda;
    private Float desconto;
    private Float valor;
    private String tipo;
    
	public FluxoCaixa(LocalDate data, Long idProduto, Long idVenda, Float desconto, Float valor, String tipo) {
		this.data = data;
		this.idProduto = idProduto;
		this.idVenda = idVenda;
		this.desconto = desconto;
		this.valor = valor;
		this.tipo = tipo;
	}
    
	
}
