package com.examencl2.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.examencl2.entity.Boleta;
import com.examencl2.entity.Producto;
import com.examencl2.entity.Usuario;
import com.examencl2.service.BoletaService;
import com.examencl2.service.ElectroService;
import com.examencl2.service.UsuarioService;

@Controller
@RequestMapping("/electro")
public class ElectroController {

	@Autowired
	ElectroService serviceelec;

	@Autowired
	UsuarioService serusu;

	@Autowired
	BoletaService serbole;

	@RequestMapping("/lis")
	public String inicio(Model model) {

		List<Producto> data = serviceelec.listarTodos();
		Producto el = new Producto();

		model.addAttribute("lista", data);
		model.addAttribute("elec", el);
		return "crud";
	}

	@RequestMapping("/catalogo")
	public String catalogo(Model model) {

		List<Producto> data = serviceelec.listarTodos();

		model.addAttribute("lista", data);

		return "cataloo";
	}

	@RequestMapping("/selecDeta")
	public String detalle(Model model, @RequestParam("txtcodigo") int cod) {

		Producto ele = serviceelec.buscar(cod);
		model.addAttribute("elec", ele);

		return "detalle";
	}

	@RequestMapping("/grabarventa")
	public String ventas(@RequestParam("txtcodigo") int codi, @RequestParam("cantidad") int cant,
			@AuthenticationPrincipal User user, RedirectAttributes redirect) {

		String usu = user.getUsername();
		Usuario u = serusu.findBynombreusuario(usu);
		Producto pro = new Producto();
		pro.setCodigo(codi);

		Usuario suario = new Usuario();
		suario.setId_usuario(u.getId_usuario());

		Boleta bol = new Boleta();
		bol.setFechaemei(new Date());
		bol.setPro(pro);
		bol.setUsu(suario);
		bol.setCantidad(cant);
		bol.setUsu(suario);

		serbole.grabarboleta(bol);
		redirect.addFlashAttribute("MENSAJE", "Compra exitosa");

		return "redirect:/electro/catalogo";
	}

	@RequestMapping("/grabar")
	public String registrar(Model model, @ModelAttribute("elec") Producto elec, RedirectAttributes redirect) {

		try {
			int cod = elec.getCodigo();
			serviceelec.grabar(elec);
			if (cod == 0) {
				redirect.addFlashAttribute("MENSAJE", "Registro exitoso");
			} else {

				redirect.addFlashAttribute("MENSAJE", "Actualizado exitoso");
			}

		} catch (Exception e) {
			System.out.println("error al grabar" + e.getMessage());
		}

		return "redirect:/electro/lis";
	}

	@RequestMapping("/buscar")
	@ResponseBody
	// convertir ese medicamento que retorna aun json
	public Producto buscar(@RequestParam("codigo") int cod) {
		Producto m = serviceelec.buscar(cod);
		System.out.println("nombre enntro==" + m.getNombre());

		return m;
	}

	// metodo eliminar
	@RequestMapping("/eliminar")
	public String Eliminar(@RequestParam("codigo") int cod, RedirectAttributes redirect) {
		try {
			serviceelec.eliminar(cod);
			redirect.addFlashAttribute("MENSAJE", "Eliminado exitoso");

		} catch (Exception e) {
			redirect.addFlashAttribute("MENSAJE", "error eliminar");
			System.out.println("error al eliiminar" + e.getMessage());
		}
		return "redirect:/electro/lis";
	}

	@RequestMapping("/subir-archivo")
	// recuperamos la caja data y almcaceno en archivo
	public String subirArchivo(@RequestParam("data") MultipartFile archivo, @RequestParam("codigo") Integer cod,
			RedirectAttributes redirect) throws IOException {

		// guardo el nombre de la imagen en una varible
		String nomArchivo = archivo.getOriginalFilename();
		Producto ele = serviceelec.buscar(cod);
		// necesito los archivos de la img pero en byte(ya que las imagnes tienes byte)
		byte[] bytes = archivo.getBytes();
		//
		String ruta = "C://ZClinica//DatosImg//";
		// generar archivo
		Files.write(Paths.get(ruta + nomArchivo), bytes);
		ele.setNombreArchivo(nomArchivo);
		serviceelec.grabar(ele);
		// serviceelec.actualizarIMG(nomArchivo, cod);
		redirect.addFlashAttribute("MENSAJE", "Foto actualizada");
		return "redirect:/electro/lis";
	}

}
