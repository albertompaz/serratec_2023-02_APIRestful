package org.serratec.backend.servicedto.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.serratec.backend.servicedto.DTO.UsuarioDTO;
import org.serratec.backend.servicedto.DTO.UsuarioInserirDTO;
import org.serratec.backend.servicedto.config.MailConfig;
import org.serratec.backend.servicedto.exception.EmailException;
import org.serratec.backend.servicedto.exception.SenhaException;
import org.serratec.backend.servicedto.model.Perfil;
import org.serratec.backend.servicedto.model.Usuario;
import org.serratec.backend.servicedto.model.UsuarioPerfil;
import org.serratec.backend.servicedto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PerfilService perfilService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private MailConfig mailConfig;

	public List<UsuarioDTO> findAll() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		/*
		 * List<UsuarioDTO> usuariosDTO = new ArrayList<>(); for (Usuario usuario:
		 * usuarios) { usuariosDTO.add(new UsuarioDTO(usuario)); }
		 */
		List<UsuarioDTO> usuariosDTO = usuarios.stream().map(usuario -> new UsuarioDTO(usuario))
				.collect(Collectors.toList());
		return usuariosDTO;
	}

	public UsuarioDTO findById(Long id) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		if (usuarioOpt.isEmpty()) {
			return null;
		}
		UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioOpt.get());
		return usuarioDTO;
	}

	@Transactional
	public UsuarioDTO inserir(UsuarioInserirDTO usuarioInserirDTO) throws EmailException {
		if (!usuarioInserirDTO.getSenha().equalsIgnoreCase(usuarioInserirDTO.getConfirmaSenha())) {
			throw new SenhaException("Senha e Confirma Senha devem ser iguais");
		}

		Usuario usuarioEmailExistente = usuarioRepository.findByEmail(usuarioInserirDTO.getEmail());
		if (usuarioEmailExistente != null) {
			throw new EmailException("Email já cadastrado.");
		}

		Usuario usuario = new Usuario();
		usuario.setNome(usuarioInserirDTO.getNome());
		usuario.setEmail(usuarioInserirDTO.getEmail());
		usuario.setSenha(bCryptPasswordEncoder.encode(usuarioInserirDTO.getSenha()));

		Set<UsuarioPerfil> usuarioPerfis = new HashSet<>();
		for (Perfil perfil : usuarioInserirDTO.getPerfis()) {
			perfil = perfilService.buscar(perfil.getId());
			UsuarioPerfil usuarioPerfil = new UsuarioPerfil(usuario, perfil);
			usuarioPerfis.add(usuarioPerfil);
		}

		usuario.setUsuarioPerfis(usuarioPerfis);

		usuario = usuarioRepository.save(usuario);
		
		mailConfig.sendEmail(usuario.getEmail(), "Cadastro de Usuario", usuario.toString());

		UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
		return usuarioDTO;
	}

}
