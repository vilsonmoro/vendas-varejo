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
public class PedidoProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_pp")
    private Long id;
	
	@Column(name = "qtd_pp", nullable = false, length = 100)
    private int quatidade;

	@Column(name = "valor_pp", length = 100)
    private Float valor_total;

	@Column(name = "observacao_pp", length = 100)
    private String observacao;

    @Column(name = "tamanho_pp", nullable = false, length = 100)
    private String tamanho;
    
    @Column(name = "desconto_pp", length = 100)
    private Float desconto;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "codpro_pp", nullable = false)
    private Produto produto;
    
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "codped_pp", nullable = false)
	private Pedido pedido;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "codusu_pp")
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

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public PedidoProduto(Long id, int quatidade, Float valor_total, String observacao, String tamanho, Float desconto,
			Produto produto, Pedido pedido, Usuario usuario) {
			this.id = id;
		this.quatidade = quatidade;
		this.valor_total = valor_total;
		this.observacao = observacao;
		this.tamanho = tamanho;
		this.desconto = desconto;
		this.produto = produto;
		this.pedido = pedido;
		this.usuario = usuario;
	}

	public PedidoProduto() {

	}
    
    
}