package org.serratec.backend.servicedto.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.servicedto.DTO.UsuarioDTO;
import org.serratec.backend.servicedto.DTO.UsuarioInserirDTO;
import org.serratec.backend.servicedto.model.Usuario;
import org.serratec.backend.servicedto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listar() {
		UserDetails detail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(detail.getUsername());
		return ResponseEntity.ok(usuarioService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> buscar(@PathVariable Long id) {
		UsuarioDTO usuarioDTO = usuarioService.findById(id);
		if (usuarioDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuarioDTO);
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> inserir(@RequestBody UsuarioInserirDTO usuarioInserirDTO) {
		UsuarioDTO usuarioDTO = usuarioService.inserir(usuarioInserirDTO);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(usuarioDTO.getId())
				.toUri();
		// return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
		return ResponseEntity.created(uri).body(usuarioDTO);
	}
	
}
