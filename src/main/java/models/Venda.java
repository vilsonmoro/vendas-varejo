package models;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_ven")
    private Long id;
	@Column(name = "data_ven", nullable = false, length = 100)
    private Date data;
	@Column(name = "comissao_ven", length = 100)
    private Float comissao;
	@Column(name = "valtot_ven", nullable = false, length = 100)
    private Float valorTotal;
	@Column(name = "qtdparcela_ven", length = 100)
    private int parcela;
	@Column(name = "observacao_ven", length = 100)
    private String observacao;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "codusu_ven")
	private Usuario usuario;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "codped_ven")
    private Pedido pedido;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "codcli_ven")
    private Cliente cliente;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "codvend_ven")
    private Vendedor vendedor;
	
    @Enumerated(EnumType.STRING)
	@Column(name = "forpag_ven", length = 25)
    private FormaPagamento forma_pagamento;
	
    @Enumerated(EnumType.STRING)
	@Column(name = "tipent_ven", length = 25)
    private TipoEntrega tipo_entrega;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Float getComissao() {
		return comissao;
	}

	public void setComissao(Float comissao) {
		this.comissao = comissao;
	}

	public Float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public int getParcela() {
		return parcela;
	}

	public void setParcela(int parcela) {
		this.parcela = parcela;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public FormaPagamento getForma_pagamento() {
		return forma_pagamento;
	}

	public void setForma_pagamento(FormaPagamento forma_pagamento) {
		this.forma_pagamento = forma_pagamento;
	}

	public TipoEntrega getTipo_entrega() {
		return tipo_entrega;
	}

	public void setTipo_entrega(TipoEntrega tipo_entrega) {
		this.tipo_entrega = tipo_entrega;
	}

	public Venda(Long id, Date data, Float comissao, Float valorTotal, int parcela, String observacao, Usuario usuario,
			Pedido pedido, Cliente cliente, Vendedor vendedor, FormaPagamento forma_pagamento,
			TipoEntrega tipo_entrega) {
		this.id = id;
		this.data = data;
		this.comissao = comissao;
		this.valorTotal = valorTotal;
		this.parcela = parcela;
		this.observacao = observacao;
		this.usuario = usuario;
		this.pedido = pedido;
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.forma_pagamento = forma_pagamento;
		this.tipo_entrega = tipo_entrega;
	}

	public Venda() {
		
	}
    
    
}