package com.examencl2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examencl2.entity.Rol;
import com.examencl2.entity.Usuario;
import com.examencl2.repository.RolRepository;

@Service
public class RolService {
	
	@Autowired
	private RolRepository repository;
	
	//listar roles
		public List<Rol> listarroles() {
				
		   return repository.findAll();
		}

}
