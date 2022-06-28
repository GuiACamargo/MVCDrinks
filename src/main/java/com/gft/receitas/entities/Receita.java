package com.gft.receitas.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Receita {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private int tempoDePreparo;
	private String modoDePreparo;
	private Boolean alcoolico;
	@OneToMany(mappedBy = "receita")
	private Set<Item> item;
		
	public Receita() {
	}

	public Receita(String nome, int tempoDePreparo, String modoDePreparo, boolean alcoolico, Set<Item> item) {
		this.nome = nome;
		this.tempoDePreparo = tempoDePreparo;
		this.modoDePreparo = modoDePreparo;
		this.alcoolico = alcoolico;
		this.item = item;
	}

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

	public Boolean getAlcoolico() {
		return alcoolico;
	}

	public void setAlcoolico(Boolean alcoolico) {
		this.alcoolico = alcoolico;
	}

	public Set<Item> getItem() {
		return item;
	}

	public void setItem(Set<Item> item) {
		this.item = item;
	}	
}
