package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Professor {
	@Id
	@GeneratedValue
	private Long id;
	private String dni;
	public String nombre;
	private String apellido;
	public String categoria;

	public Professor(Long id, String dni, String nombre, String apellido, String categoria) {
		super();
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.categoria = categoria;

	}
	public Professor() {
		
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getnombre() {
		return nombre;
	}

	public void setnombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCategory() {
		return categoria;
	}

	public void setCategory(String categoria) {
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(long l) {
		this.id = l;
	}

	@Override
	public String toString() {
		return "Professor [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", categoria="
				+ categoria + "]";
	}
}
