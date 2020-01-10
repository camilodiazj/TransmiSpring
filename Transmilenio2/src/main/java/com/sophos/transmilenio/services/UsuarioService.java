package com.sophos.transmilenio.services;



import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sophos.transmilenio.beans.Usuario;
import com.sophos.transmilenio.daos.IUsuarioDao;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService{
	
	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//el valor retornado UserDeails es el usuario de Spring Security 
		Usuario usuario = usuarioDao.findByUsername(username);
		
		if(usuario == null) {
			logger.error("Error en el login: no existe el usuario "+ username +" en el sistema.");
			throw new UsernameNotFoundException("Error en el login: no existe el usuario "+ username +" en el sistema.");
		}
		
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());  //Se tomó la lista de roles List, se convirtion a una List de tipo Simple... con el map  
		
		return new User(username, usuario.getPassword(), usuario.isEnabled(), true, true, true, authorities);
	} //interface de Spring Security

	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}
	
	
}
