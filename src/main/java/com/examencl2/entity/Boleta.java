package com.examencl2.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_boleta")
public class Boleta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_bole")
	private int codigobol;

	private int cantidad;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usu;

	@ManyToOne
	@JoinColumn(name = "codigo")
	private Producto pro;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaemei;

	public int getCodigobol() {
		return codigobol;
	}

	public void setCodigobol(int codigobol) {
		this.codigobol = codigobol;
	}

	public Usuario getUsu() {
		return usu;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void setUsu(Usuario usu) {
		this.usu = usu;
	}

	public Producto getPro() {
		return pro;
	}

	public void setPro(Producto pro) {
		this.pro = pro;
	}

	public Date getFechaemei() {
		return fechaemei;
	}

	public void setFechaemei(Date fechaemei) {
		this.fechaemei = fechaemei;
	}

}
