package models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProdutoVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_pv")
    private Long id;
	@Column(name = "qtd_pv", nullable = false, length = 100)
    private int quatidade;
	@Column(name = "tamanho_pv", nullable = false, length = 100)
    private String tamanho;
	@Column(name = "desconto_pv", length = 100)
    private Float desconto;
	@Column(name = "valor_pv", length = 100)
    private Float valor_total;
	@Column(name = "observacao_pv", length = 100)
	private String observacao;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "codpro_pv", nullable = false)
    private Produto produto;
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "codven_pv", nullable = false)
    private Venda venda;
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "codusu_pv")
	private Usuario usuario;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getQuatidade() {
		return quatidade;
	}
	public void setQuatidade(int quatidade) {
		this.quatidade = quatidade;
	}
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	public Float getDesconto() {
		return desconto;
	}
	public void setDesconto(Float desconto) {
		this.desconto = desconto;
	}
	public Float getValor_total() {
		return valor_total;
	}
	public void setValor_total(Float valor_total) {
		this.valor_total = valor_total;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public ProdutoVenda(Long id, int quatidade, String tamanho, Float desconto, Float valor_total, String observacao,
			Produto produto, Venda venda, Usuario usuario) {
		this.id = id;
		this.quatidade = quatidade;
		this.tamanho = tamanho;
		this.desconto = desconto;
		this.valor_total = valor_total;
		this.observacao = observacao;
		this.produto = produto;
		this.venda = venda;
		this.usuario = usuario;
	}
	
	public ProdutoVenda() {
		
	}
	
	
}