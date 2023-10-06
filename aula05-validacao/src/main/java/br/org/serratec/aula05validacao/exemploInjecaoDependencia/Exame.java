package br.org.serratec.aula05validacao.exemploInjecaoDependencia;

import org.springframework.stereotype.Component;

@Component
public class Exame {
	public Double calcularExame(Double valor) {
		return valor = valor + (valor*0.05);
	}
}
