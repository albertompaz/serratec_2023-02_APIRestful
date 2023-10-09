package org.serratec.h2.aula06H2banco.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.serratec.h2.aula06H2banco.enums.Categoria;
import org.serratec.h2.aula06H2banco.enums.Combustivel;

@Embeddable
public class Caracteristica {

	@Column
	private String renavam;

	@Column
	private String chassi;

	@Column
	private Long ano;

	@Column
	@Enumerated(EnumType.STRING)
	private Categoria categoria;

	@Column
	@Enumerated(EnumType.ORDINAL)
	private Combustivel combustivel;

	@Column
	private String cor;

	public String getRenavam() {
		return renavam;
	}

	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public Long getAno() {
		return ano;
	}

	public void setAno(Long ano) {
		this.ano = ano;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Combustivel getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(Combustivel combustivel) {
		this.combustivel = combustivel;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

}
