package com.examencl2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examencl2.entity.Boleta;
import com.examencl2.repository.BoletaRepository;

@Service
public class BoletaService {

	@Autowired
	private BoletaRepository repobol;

	public void grabarboleta(Boleta bol) {

		repobol.save(bol);
	}

}
