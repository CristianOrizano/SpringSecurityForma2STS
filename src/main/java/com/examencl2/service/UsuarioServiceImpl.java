package com.examencl2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examencl2.entity.Usuario;
import com.examencl2.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public void grabar(Usuario usu) {
		repository.save(usu);
		
	}

	@Override
	public List<Usuario> listarusua() {
		 return repository.findAll();
	}

	@Override
	public Usuario buscarUser(int cod) {
		 return  repository.findById(cod).orElse(null);
	}

	@Override
	public void eliminar(int cod) {
		repository.deleteById(cod);
		
	}

	@Override
	public Usuario findBynombreusuario(String username) {
		return repository.findByUsername(username);
	}

}
