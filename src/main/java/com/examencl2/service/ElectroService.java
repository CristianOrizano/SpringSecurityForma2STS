package com.examencl2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examencl2.entity.Producto;
import com.examencl2.repository.ElectroRepository;

@Service
public class ElectroService {

	@Autowired
	private ElectroRepository repo;

	// graba o actualiza
	public void grabar(Producto bean) {
		repo.save(bean);
	}

	public void eliminar(Integer cod) {
		repo.deleteById(cod);
	}

	public Producto buscar(Integer cod) {
		return repo.findById(cod).orElse(null);
	}

	public List<Producto> listarTodos() {
		return repo.findAll();
	}

	/*
	 * public void actualizarIMG(String nomAr,Integer cod) {
	 * repo.actualizarFoto(nomAr, cod); }
	 */

	// disminuir el stock
	/*
	 * public void actualizarstock(int stock,int codigo) {
	 * repo.actualizarStock(stock, codigo); }
	 */

}
