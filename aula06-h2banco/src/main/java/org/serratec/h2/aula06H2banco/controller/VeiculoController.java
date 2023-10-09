package org.serratec.h2.aula06H2banco.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.serratec.h2.aula06H2banco.exception.EnumValidationException;
import org.serratec.h2.aula06H2banco.model.Veiculo;
import org.serratec.h2.aula06H2banco.repository.VeiculoRepository;
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

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@GetMapping
	public ResponseEntity<List<Veiculo>> listar() throws EnumValidationException {
		/*
		 * Caso alguma validação seja quebrada e eu queira lançar uma exceção customizada no meu sistema,
		 * como eu faço para lançar e capturar?
		 * 
		 * Ignorem o metodo get, e pensem como se fosse uma regra de negocio quebrada
		 * ex: um usuario pode ter cadastrado 3 contas, porem na 4 tem que da erro
		 *
		*/
		// if (tempo < 30) {
			throw new EnumValidationException("A requisição não pode ser feita dentro de 30 minutos da ultima");
		//}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Veiculo> buscar(@PathVariable Long id) {
		Optional<Veiculo> veiculoOpt = veiculoRepository.findById(id);
		if (veiculoOpt.isPresent()) {
			return ResponseEntity.ok(veiculoOpt.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Veiculo> inserir(@Valid @RequestBody Veiculo veiculo) {
		veiculo = veiculoRepository.save(veiculo);
		return ResponseEntity.status(HttpStatus.CREATED).body(veiculo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Veiculo> atualizar(@PathVariable Long id, @Valid @RequestBody Veiculo veiculo) {
		Optional<Veiculo> veiculoOpt = veiculoRepository.findById(id);
		if (veiculoOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		veiculo.setId(id);
		veiculo = veiculoRepository.save(veiculo);
		return ResponseEntity.ok(veiculo);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		Optional<Veiculo> veiculoOpt = veiculoRepository.findById(id);
		if (veiculoOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		veiculoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
