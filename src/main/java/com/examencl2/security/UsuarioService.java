package com.examencl2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examencl2.entity.Rol;
import com.examencl2.entity.Usuario;
import com.examencl2.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails us = null;
		Usuario usuario = repository.findByUsername(username);

		if (usuario == null) {
			throw new UsernameNotFoundException(username);
		}
		var roles = new ArrayList<GrantedAuthority>();
		
		//obtenemos los roles, para obtener el nombre
		for (Rol rol : usuario.getRoles()) {
			roles.add(new SimpleGrantedAuthority(rol.getNombre()));
		}
		
		System.out.println(usuario.toString());
		us = new User(usuario.getUsername(), usuario.getPassword(), roles);
		return us;
	}

	public Usuario findBynombreusuario(String username) {

		return repository.findByUsername(username);
	}
	
	//update or add
	public void grabar(Usuario usu) {
		
		
		repository.save(usu);
	}
	//listar usuarios
	public List<Usuario> listarusua() {
			
	   return repository.findAll();
	}
		//delete user
	public void eliminar(int cod) {
			repository.deleteById(cod);
	}
	
	//search to update
	public Usuario buscarUser(int cod) {
 
		 return  repository.findById(cod).orElse(null);
	}
	
	/*public Usuario buscarUser(int cod) {
		Usuario us = null;
		us =  repository.findById(cod).orElse(null);
		  return us;
	}*/
	
	
	
	
	
	
	
	
	
	
	

}
