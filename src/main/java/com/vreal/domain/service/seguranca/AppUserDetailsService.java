package com.vreal.domain.service.seguranca;

import java.util.*;

import com.vreal.domain.model.seguranca.dto.PermissaoTarefaDTO;
import com.vreal.domain.model.seguranca.dto.UsuarioSistemaDTO;
import com.vreal.domain.model.seguranca.model.Usuario;
import com.vreal.domain.repository.seguranca.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class AppUserDetailsService implements UserDetailsService {


	private UsuarioRepository usuarioRepository;
	private UsuarioService usuarioService;

	@Autowired
	public AppUserDetailsService(UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
		this.usuarioRepository = usuarioRepository;
		this.usuarioService = usuarioService;
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		Optional<Usuario> usuOptional = usuarioRepository.findByLogin(login);
		Usuario usuario = usuOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos "));
		return new UsuarioSistemaDTO(usuario, getPermissoes(usuario));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {

		Set<SimpleGrantedAuthority> authorities = new HashSet<>();

		List<PermissaoTarefaDTO> lista = usuarioService.listaDePermissoes(usuario);
		lista.forEach(p -> authorities.add(new SimpleGrantedAuthority(p.tarefa.getDescricaoRole().toUpperCase())));

		return authorities;
	}
}


