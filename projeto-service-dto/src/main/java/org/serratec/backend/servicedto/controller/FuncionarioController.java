package org.serratec.backend.servicedto.controller;

import java.io.IOException;
import java.util.List;

import org.serratec.backend.servicedto.DTO.FuncionarioDTO;
import org.serratec.backend.servicedto.DTO.FuncionarioSalarioDTO;
import org.serratec.backend.servicedto.model.Foto;
import org.serratec.backend.servicedto.model.Funcionario;
import org.serratec.backend.servicedto.repository.FuncionarioRepository;
import org.serratec.backend.servicedto.service.FotoService;
import org.serratec.backend.servicedto.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@Autowired
	FuncionarioService funcionarioService;
	
	@Autowired
	FotoService fotoService;
	
	@GetMapping
	public ResponseEntity<List<Funcionario>> listar() {
		List<Funcionario> funcionarios = funcionarioRepository.findAll();
		return ResponseEntity.ok(funcionarios);
	}

	@GetMapping("/paginado")
	public ResponseEntity<Page<Funcionario>> listar(
			@PageableDefault(sort="nome", direction=Direction.ASC, page=0, size=5) Pageable pageable) {
		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
		return ResponseEntity.ok(funcionarios);
	}
	
	@GetMapping("/salario")
	public ResponseEntity<Page<Funcionario>> listarSalarios(
			@RequestParam(defaultValue = "0") Double valorMinimo,
			@RequestParam(defaultValue = "20000") Double valorMaximo, 
			Pageable pageable) {
		Page<Funcionario> funcionarios = funcionarioRepository.buscarSalario(valorMinimo, valorMaximo, pageable);
		return ResponseEntity.ok(funcionarios);
	}
	
	@GetMapping("/nome")
	public ResponseEntity<Page<Funcionario>> listarPorNome(
			@RequestParam(defaultValue = "") String paramNome, Pageable pageable) {
		Page<Funcionario> funcionarios = funcionarioRepository.buscarPorNome(paramNome, pageable);
		return ResponseEntity.ok(funcionarios);
	}
	
	@GetMapping("/salarios-por-idade")
	public ResponseEntity<List<FuncionarioSalarioDTO>> buscarSalariosPorIdade() {
		List<FuncionarioSalarioDTO> buscaSalariosPorIdade = funcionarioRepository.buscaSalariosPorIdade();
		buscaSalariosPorIdade.forEach(el -> {
			System.out.println(el.getMaiorSalario());
		});
		return ResponseEntity.ok(buscaSalariosPorIdade);
	}
	
	///////////// Metodos com o Service de funcionario /////////////////
	
	@GetMapping("/listarDTO")
	public ResponseEntity<List<FuncionarioDTO>> listarDTO() {
		List<FuncionarioDTO> funcionarios = funcionarioService.listar();
		return ResponseEntity.ok(funcionarios);
	}
	
	@GetMapping("/{id}/foto")
	public ResponseEntity<byte[]> buscarFoto(@PathVariable Long id) {
		Foto foto = fotoService.buscarPorIdFuncionario(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, foto.getTipo());
		headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(foto.getDados().length));
		return new ResponseEntity<>(foto.getDados(), headers, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FuncionarioDTO> buscar(@PathVariable Long id) {
		FuncionarioDTO funcOpt = funcionarioService.buscar(id);
		if (funcOpt == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(funcOpt);
	}
	
	@PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<FuncionarioDTO> inserir(
			@RequestPart MultipartFile file, @RequestPart Funcionario funcionario) throws IOException {
		FuncionarioDTO funcionarioDTO = funcionarioService.inserir(file, funcionario);
		return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioDTO);
	}

}
