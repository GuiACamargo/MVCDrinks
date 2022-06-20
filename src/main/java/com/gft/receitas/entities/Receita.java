package com.gft.receitas.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Receita {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private int tempoDePreparo;
	private String modoDePreparo;
	@ManyToMany
	private List<Ingrediente> ingredientes;
	@ManyToMany
	private List<UnidadeMedida> unidadeMedidas;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getTempoDePreparo() {
		return tempoDePreparo;
	}
	
	public void setTempoDePreparo(int tempoDePreparo) {
		this.tempoDePreparo = tempoDePreparo;
	}
	
	public String getModoDePreparo() {
		return modoDePreparo;
	}
	
	public void setModoDePreparo(String modoDePreparo) {
		this.modoDePreparo = modoDePreparo;
	}
	
	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}
	
	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}
	
	public List<UnidadeMedida> getUnidadeMedidas() {
		return unidadeMedidas;
	}
	
	public void setUnidadeMedidas(List<UnidadeMedida> unidadeMedidas) {
		this.unidadeMedidas = unidadeMedidas;
	}
	
}
