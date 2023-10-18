package org.serratec.backend.servicedto.service;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.serratec.backend.servicedto.DTO.FuncionarioDTO;
import org.serratec.backend.servicedto.model.Funcionario;
import org.serratec.backend.servicedto.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private FotoService fotoService;
	
	public List<FuncionarioDTO> listar() {
		List<Funcionario> funcionarioList = funcionarioRepository.findAll();
		
		List<FuncionarioDTO> funcionarioDtoList = funcionarioList.stream().map(func -> {
			return adicionaImagemURI(func);
		}).collect(Collectors.toList());
		
		return funcionarioDtoList;
	}
	
	public FuncionarioDTO adicionaImagemURI(Funcionario funcionario) {
		URI uri = ServletUriComponentsBuilder.
				fromCurrentContextPath()
				.path("/funcionarios/{id}/foto")
				.buildAndExpand(funcionario.getId())
				.toUri();
		
		FuncionarioDTO dto = new FuncionarioDTO();
		dto.setNome(funcionario.getNome());
		dto.setDataNascimento(funcionario.getDataNascimento());
		dto.setSalario(funcionario.getSalario());
		dto.setUrl(uri.toString());
		return dto;
	}
	
	public FuncionarioDTO buscar(Long id) {
		Optional<Funcionario> funcionarioOpt = funcionarioRepository.findById(id);
		if (funcionarioOpt.isEmpty()) {
			return null;
		}
		return adicionaImagemURI(funcionarioOpt.get());
	}
	
	public FuncionarioDTO inserir(MultipartFile file, Funcionario funcionario) throws IOException {
		funcionario = funcionarioRepository.save(funcionario);
		fotoService.inserir(funcionario, file);
		return adicionaImagemURI(funcionario);
	}
	
}
