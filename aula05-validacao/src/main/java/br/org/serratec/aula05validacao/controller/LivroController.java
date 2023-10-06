package br.org.serratec.aula05validacao.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.aula05validacao.model.Livro;
import br.org.serratec.aula05validacao.repository.LivroRepository;

@RestController
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	private LivroRepository livroRepository;
	
	@GetMapping
	public ResponseEntity<List<Livro>> listar() {
		List<Livro> livros = livroRepository.findAll();
		return ResponseEntity.ok(livros);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Livro> buscar(@PathVariable Long id) {
		Optional<Livro> livroOpt = livroRepository.findById(id);
		if (livroOpt.isPresent()) {
			return ResponseEntity.ok(livroOpt.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	/*
	 * Para que meu JSON que veio do resqust seja validado, é necessário usar a anotação @Valid 
	 * */
	public ResponseEntity<Livro> inserir(@Valid @RequestBody Livro livro) {
		livro = livroRepository.save(livro);
		return ResponseEntity.status(HttpStatus.CREATED).body(livro);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Livro> atualizar(@PathVariable Long id, @Valid @RequestBody Livro livro) {
		Optional<Livro> livroOpt = livroRepository.findById(id);
		if (livroOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		livro.setId(id);
		livro = livroRepository.save(livro);
		return ResponseEntity.ok(livro);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Optional<Livro> livroOpt = livroRepository.findById(id);
		if (livroOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		livroRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
