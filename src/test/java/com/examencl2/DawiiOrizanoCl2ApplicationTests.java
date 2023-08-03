package com.examencl2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.examencl2.entity.Producto;
import com.examencl2.service.ElectroService;

@SpringBootTest
class DawiiOrizanoCl2ApplicationTests {

	@Autowired
	ElectroService serviceelec;

	@Test
	void contextLoads() {
		Producto elec = new Producto();
		elec.setCodigo(0);
		elec.setNombre("olla2023");
		elec.setStock(20);
		elec.setPrec(20);
		elec.setMarca("undur");
		elec.setNombreArchivo("foto");

		serviceelec.grabar(elec);

	}

}
