package br.org.serratec.exerciciojpa.controller;

import java.util.List;
import java.util.Optional;

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

import br.org.serratec.exerciciojpa.domain.Produto;
import br.org.serratec.exerciciojpa.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public List<Produto> listar() {
		return produtoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> pesquisar(@PathVariable Long id) {
		Optional<Produto> produtoOpt = produtoRepository.findById(id);
		if (produtoOpt.isPresent()) {
			return ResponseEntity.ok(produtoOpt.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Produto> inserir(@RequestBody Produto produto) {
		produto = produtoRepository.save(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(produto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizar(@RequestBody Produto produto, @PathVariable Long id) {
		Optional<Produto> produtoOpt = produtoRepository.findById(id);
		if (produtoOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		/*
		produto.setId(id);
		produto = produtoRepository.save(produto);
		*/
		
		Produto produtoBD = produtoOpt.get();
		produtoBD.setDataCadastro(produto.getDataCadastro());
		produtoBD.setDescricao(produto.getDescricao());
		produtoBD.setValor(produto.getValor());
		
		produto = produtoRepository.save(produtoBD);
		return ResponseEntity.ok(produto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Optional<Produto> produtoOpt = produtoRepository.findById(id);
		if (produtoOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		produtoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
