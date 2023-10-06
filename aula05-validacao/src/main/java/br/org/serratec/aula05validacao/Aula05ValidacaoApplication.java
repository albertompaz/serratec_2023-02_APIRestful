package br.org.serratec.aula05validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.org.serratec.aula05validacao.exemploInjecaoDependencia.Consulta;
import br.org.serratec.aula05validacao.exemploInjecaoDependencia.Exame;
import br.org.serratec.aula05validacao.exemploInjecaoDependencia.Pagamento;

@SpringBootApplication
public class Aula05ValidacaoApplication implements CommandLineRunner {

	@Autowired
	private Pagamento pagamento;
	
	public static void main(String[] args) {
		SpringApplication.run(Aula05ValidacaoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Total a pagar: " + pagamento.calcularProcedimento(200.0, 80.0));
	}

}
