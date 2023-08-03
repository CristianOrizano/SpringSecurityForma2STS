package com.examencl2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examencl2.entity.Producto;
import com.examencl2.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Usuario findByUsername(String username);

}
