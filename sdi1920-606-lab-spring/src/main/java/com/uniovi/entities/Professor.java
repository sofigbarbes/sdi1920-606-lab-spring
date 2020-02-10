package com.uniovi.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Professor {
	@Id
	@GeneratedValue
	private Long id;
	private String dni;
	private String name;
	private String surname;
	private String category;

	public Professor(Long id, String dni, String name, String surname, String category) {
		super();
		this.id = id;
		this.dni = dni;
		this.name = name;
		this.surname = surname;
		this.category = category;

	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public void setId(long l) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "DNI: "+dni+" - Nombre: "+name+" "+surname;
	}
}
