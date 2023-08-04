package com.examencl2.controllerApi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examencl2.entity.Producto;
import com.examencl2.service.ElectroService;

@RestController
@RequestMapping("/apielec")
public class ProductoApiController {
	
	@Autowired
	ElectroService serviceelec;
	
	@GetMapping("/api")
	public List<Producto> listar(Model model) {
		
		List<Producto> data= serviceelec.listarTodos();
	
		return data;
	}
	
	@GetMapping("/api/{id}")
	public Producto buscarProducto(Model model,@PathVariable("id") int cod) {
		
		Producto data= serviceelec.buscar(cod);
	
		return data;
	}
	
	@PostMapping("/api")
	public Producto agregar(@RequestBody Producto prod ) {
	
		return serviceelec.grabar(prod);
	
	}
	@DeleteMapping("/api/{cod}")
	public void eliminar(Model model,@PathVariable ("cod") int cod) {
		serviceelec.eliminar(cod);
	}
	
	@PutMapping("/api")
	public void update(Model model,@RequestBody Producto producto) {
		
		serviceelec.grabar(producto);
	}
	
	

}
