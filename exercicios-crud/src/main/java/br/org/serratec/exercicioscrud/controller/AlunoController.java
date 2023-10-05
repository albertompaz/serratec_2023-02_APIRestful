package br.org.serratec.exercicioscrud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.exercicioscrud.domain.Aluno;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
	
	private static List<Aluno> lista = new ArrayList<>();
	static {
		lista.add(new Aluno(1111L, "Fulano", "2222-2222"));
		lista.add(new Aluno(2222L, "Beltrano", "4444-4444"));
		lista.add(new Aluno(3333L, "Ciclano", "6666-6666"));
	}
	
	@GetMapping
	public List<Aluno> listar() {
		return lista;
	}
	
	@GetMapping("/{matricula}")
	public Aluno buscar(@PathVariable Long matricula) {
		/*
		for (int i=0; i<lista.size(); i++) {
			if (lista.get(i).getMatricula().equals(matricula)) {
				return lista.get(i);
			}
		}
		return null;
		*/
		return lista.stream().filter(a -> a.getMatricula().equals(matricula)).findFirst().orElse(null);
	}
	
	@PostMapping
	public Aluno inserir(@RequestBody Aluno aluno) {
		lista.add(aluno);
		return aluno;
	}
	
	@DeleteMapping("/{matricula}")
	public void delete(@PathVariable Long matricula) {
		/*
		for (int i=0; i<lista.size(); i++) {
			if (lista.get(i).getMatricula().equals(matricula)) {
				lista.remove(i);
				break;
			}
		}
		*/
		lista = lista.stream().filter(a -> !a.getMatricula().equals(matricula)).collect(Collectors.toList());
	}
	
	@PutMapping("/{matricula}")
	public Aluno atualizar(@RequestBody Aluno aluno, @PathVariable Long matricula) {
		for (int i=0; i<lista.size(); i++) {
			if (lista.get(i).getMatricula().equals(matricula)) {
				Aluno a = new Aluno(matricula, aluno.getNome(), aluno.getTelefone());
				lista.set(i, a);
				return a;
			}
		}
		return null;
	}

}
