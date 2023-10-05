package br.org.serratec.exercicioscrud.domain;

public class Aluno {

	private Long matricula;
	private String nome;
	private String telefone;

	public Aluno() {}
	
	public Aluno(Long matricula, String nome, String telefone) {
		this.matricula = matricula;
		this.nome = nome;
		this.telefone = telefone;
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
