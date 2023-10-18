package org.serratec.backend.servicedto.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.serratec.backend.servicedto.model.Usuario;
import org.serratec.backend.servicedto.model.UsuarioPerfil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioDetalhe implements UserDetails {

	private Usuario usuario;
	
	public UsuarioDetalhe(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorites = new ArrayList<>();
		for (UsuarioPerfil usuarioPerfil: usuario.getUsuarioPerfis()) {
			authorites.add(new SimpleGrantedAuthority(usuarioPerfil.getId().getPerfil().getNome()));
		}
		return authorites;
	}

	@Override
	public String getPassword() {
		return usuario.getSenha();
	}

	@Override
	public String getUsername() {
		return usuario.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
