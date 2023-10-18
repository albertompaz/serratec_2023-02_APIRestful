package org.serratec.backend.servicedto.service;

import java.io.IOException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.serratec.backend.servicedto.model.Foto;
import org.serratec.backend.servicedto.model.Funcionario;
import org.serratec.backend.servicedto.repository.FotoRepository;
import org.serratec.backend.servicedto.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotoService {

	@Autowired
	private FotoRepository fotoRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public Foto inserir(Funcionario funcionario, MultipartFile file) throws IOException {
		Foto foto = new Foto();
		foto.setNome(file.getName());
		foto.setTipo(file.getContentType());
		foto.setDados(file.getBytes());
		foto.setFuncionario(funcionario);
		
		foto = fotoRepository.save(foto);
		
		return foto;
	}
	
	@Transactional
	public Foto buscarPorIdFuncionario(Long id) {
		Optional<Funcionario> funcionarioOpt = funcionarioRepository.findById(id);
		if (funcionarioOpt.isEmpty()) {
			return null;
		}
		
		Optional<Foto> fotoOpt = fotoRepository.findByFuncionario(funcionarioOpt.get());
		if (fotoOpt.isEmpty()) {
			return null;
		}
		
		return fotoOpt.get();
	}
	
}
