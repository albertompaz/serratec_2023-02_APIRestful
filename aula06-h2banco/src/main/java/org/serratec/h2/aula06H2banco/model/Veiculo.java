package org.serratec.h2.aula06H2banco.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "veiculo")
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Preencha a placa do veiculo")
	@Size(max = 7)
	@Column(nullable = false, length = 7)
	private String placa;

	@NotBlank(message = "Preencha a marca do veiculo")
	@Size(max = 30)
	@Column(nullable = false, length = 30)
	private String marca;

	@NotBlank(message = "Preencha o modelo do veiculo")
	@Size(max = 40)
	@Column(nullable = false, length = 40)
	private String modelo;

	@Embedded
	private Caracteristica caracteristica;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Caracteristica getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(Caracteristica caracteristica) {
		this.caracteristica = caracteristica;
	}

}
