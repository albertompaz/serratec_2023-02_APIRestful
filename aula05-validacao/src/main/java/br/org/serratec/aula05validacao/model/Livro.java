package br.org.serratec.aula05validacao.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "livro")
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	/* Anotações do javax.validation -> validação na verificação do JSON que chega na API com a classe java
	 * para qual sera convertida
	 */
	@NotBlank
	@Size(max = 50)
	// @Column -> validação entre classe java e o banco de daados
	@Column(name = "titulo", nullable = false, length = 50)
	private String titulo;

	@Column
	private String autor;

	@Column(name = "qtd_paginas")
	private Integer qtdPaginas;

	@DecimalMax(value = "5000", message = "O preço nao pode ser maior que R${value}.00")
	@DecimalMin(value = "2", message = "O preço nao pode ser menor que R${value}.00")
	@Column
	private BigDecimal valor;

	public Livro() {
	}

	public Livro(Long id, String titulo, String autor, Integer qtdPaginas, BigDecimal valor) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.qtdPaginas = qtdPaginas;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Integer getQtdPaginas() {
		return qtdPaginas;
	}

	public void setQtdPaginas(Integer qtdPaginas) {
		this.qtdPaginas = qtdPaginas;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
