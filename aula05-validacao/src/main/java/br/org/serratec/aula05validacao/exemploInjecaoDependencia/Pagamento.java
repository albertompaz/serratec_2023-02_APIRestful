package br.org.serratec.aula05validacao.exemploInjecaoDependencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Pagamento {
	@Autowired
	private Consulta consulta;
	@Autowired
	private Exame exame;
	
	/*
	public Pagamento(Consulta consulta, Exame exame) {
		this.consulta = consulta;
		this.exame = exame;
	}
	*/
	
	public Double calcularProcedimento(Double valorConsulta, Double valorExame) {
		return consulta.calcularConsulta(valorConsulta) + exame.calcularExame(valorExame);
	}
}
