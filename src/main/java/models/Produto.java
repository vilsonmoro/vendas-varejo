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
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_pro")
	private Long id;

	@Column(name = "nome_pro", nullable = false, length = 100)
	private String nome;
	@Column(name = "peso_pro", length = 100)
	private Float peso;
	@Column(name = "modelagem_pro", length = 100)
	private Float modelagem;
	@Column(name = "desenvolvimento_pro", length = 100)
	private Float desenvolvimento;
	@Column(name = "corte_pro", length = 100)
	private Float corte;
	@Column(name = "malha_pro", length = 100)
	private Float malha;
	@Column(name = "etimarc_pro", length = 100)
	private Float etiquetaMarca;
	@Column(name = "eticomp_pro", length = 100)
	private Float etiquetaComposicao;
	@Column(name = "tag_pro", length = 100)
	private Float tag;
	@Column(name = "refil_pro", length = 100)
	private Float refilPistola;
	@Column(name = "sacopla_pro", length = 100)
	private Float sacoPlastico;
	@Column(name = "fita_pro", length = 100)
	private Float fita;
	@Column(name = "elastico_pro", length = 100)
	private Float elastico;
	@Column(name = "valcostura_pro", length = 100)
	private Float precoCostura;
	@Column(name = "embalagem_pro", length = 100)
	private Float embalagem;
	@Column(name = "custo_pro", length = 100)
	private Float custoProduto;
	@Column(name = "fatormult_pro", nullable = false, length = 100)
	private Float fatormult;
	@Column(name = "valoratacado_pro", length = 100)
	private Float valoratacado;
	@Column(name = "valorvarejo_pro", length = 100)
	private Float valorvarejo;
	@Column(name = "deslocamento_pro", length = 100)
	private Float deslocamento;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "codusu_pro")
	private Usuario usuario;

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

	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}

	public Float getModelagem() {
		return modelagem;
	}

	public void setModelagem(Float modelagem) {
		this.modelagem = modelagem;
	}

	public Float getDesenvolvimento() {
		return desenvolvimento;
	}

	public void setDesenvolvimento(Float desenvolvimento) {
		this.desenvolvimento = desenvolvimento;
	}

	public Float getCorte() {
		return corte;
	}

	public void setCorte(Float corte) {
		this.corte = corte;
	}

	public Float getMalha() {
		return malha;
	}

	public void setMalha(Float malha) {
		this.malha = malha;
	}

	public Float getEtiquetaMarca() {
		return etiquetaMarca;
	}

	public void setEtiquetaMarca(Float etiquetaMarca) {
		this.etiquetaMarca = etiquetaMarca;
	}

	public Float getEtiquetaComposicao() {
		return etiquetaComposicao;
	}

	public void setEtiquetaComposicao(Float etiquetaComposicao) {
		this.etiquetaComposicao = etiquetaComposicao;
	}

	public Float getTag() {
		return tag;
	}

	public void setTag(Float tag) {
		this.tag = tag;
	}

	public Float getRefilPistola() {
		return refilPistola;
	}

	public void setRefilPistola(Float refilPistola) {
		this.refilPistola = refilPistola;
	}

	public Float getSacoPlastico() {
		return sacoPlastico;
	}

	public void setSacoPlastico(Float sacoPlastico) {
		this.sacoPlastico = sacoPlastico;
	}

	public Float getFita() {
		return fita;
	}

	public void setFita(Float fita) {
		this.fita = fita;
	}

	public Float getElastico() {
		return elastico;
	}

	public void setElastico(Float elastico) {
		this.elastico = elastico;
	}

	public Float getPrecoCostura() {
		return precoCostura;
	}

	public void setPrecoCostura(Float precoCostura) {
		this.precoCostura = precoCostura;
	}

	public Float getEmbalagem() {
		return embalagem;
	}

	public void setEmbalagem(Float embalagem) {
		this.embalagem = embalagem;
	}

	public Float getCustoProduto() {
		return custoProduto;
	}

	public void setCustoProduto(Float custoProduto) {
		this.custoProduto = custoProduto;
	}

	public Float getFatormult() {
		return fatormult;
	}

	public void setFatormult(Float fatormult) {
		this.fatormult = fatormult;
	}

	public Float getValoratacado() {
		return valoratacado;
	}

	public void setValoratacado(Float valoratacado) {
		this.valoratacado = valoratacado;
	}

	public Float getValorvarejo() {
		return valorvarejo;
	}

	public void setValorvarejo(Float valorvarejo) {
		this.valorvarejo = valorvarejo;
	}

	public Float getDeslocamento() {
		return deslocamento;
	}

	public void setDeslocamento(Float deslocamento) {
		this.deslocamento = deslocamento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Produto(Long id, String nome, Float peso, Float modelagem, Float desenvolvimento, Float corte, Float malha,
			Float etiquetaMarca, Float etiquetaComposicao, Float tag, Float refilPistola, Float sacoPlastico,
			Float fita, Float elastico, Float precoCostura, Float embalagem, Float custoProduto, Float fatormult,
			Float valoratacado, Float valorvarejo, Float deslocamento, Usuario usuario) {
		this.id = id;
		this.nome = nome;
		this.peso = peso;
		this.modelagem = modelagem;
		this.desenvolvimento = desenvolvimento;
		this.corte = corte;
		this.malha = malha;
		this.etiquetaMarca = etiquetaMarca;
		this.etiquetaComposicao = etiquetaComposicao;
		this.tag = tag;
		this.refilPistola = refilPistola;
		this.sacoPlastico = sacoPlastico;
		this.fita = fita;
		this.elastico = elastico;
		this.precoCostura = precoCostura;
		this.embalagem = embalagem;
		this.custoProduto = custoProduto;
		this.fatormult = fatormult;
		this.valoratacado = valoratacado;
		this.valorvarejo = valorvarejo;
		this.deslocamento = deslocamento;
		this.usuario = usuario;
	}

	public Produto() {

	}

}