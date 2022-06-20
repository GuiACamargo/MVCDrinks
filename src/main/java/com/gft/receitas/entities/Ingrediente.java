package com.gft.receitas.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Ingrediente {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	
	private String nome;
	@ManyToMany(mappedBy = "ingredientes")
	private List<Receita> receitas;

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
	
	public List<Receita> getReceitas() {
		return receitas;
	}
	
	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}
	
}
