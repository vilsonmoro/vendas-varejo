package models;

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
import jakarta.persistence.OneToOne;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_cli")
    private Long id;

    @Column(name = "nome_cli", nullable = false, length = 100)
    private String nome;
    
    @Column(name = "observacao_cli", length = 255)
    private String observacao;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "codusu_cli")
    private Usuario usuario;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "codend_cli")
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipent_cli", length = 25)
    private TipoEntrega tipo_entrega;

    @Enumerated(EnumType.STRING)
    @Column(name = "tippag_cli", length = 25)
    private FormaPagamento forma_pagamento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public TipoEntrega getTipo_entrega() {
		return tipo_entrega;
	}

	public void setTipo_entrega(TipoEntrega tipo_entrega) {
		this.tipo_entrega = tipo_entrega;
	}

	public FormaPagamento getForma_pagamento() {
		return forma_pagamento;
	}

	public void setForma_pagamento(FormaPagamento forma_pagamento) {
		this.forma_pagamento = forma_pagamento;
	}

	public Cliente(Long id, String nome, String observacao, Usuario usuario, Endereco endereco,
			TipoEntrega tipo_entrega, FormaPagamento forma_pagamento) {
		super();
		this.id = id;
		this.nome = nome;
		this.observacao = observacao;
		this.usuario = usuario;
		this.endereco = endereco;
		this.tipo_entrega = tipo_entrega;
		this.forma_pagamento = forma_pagamento;
	}

	public Cliente() {
		
	} 
    
    
}