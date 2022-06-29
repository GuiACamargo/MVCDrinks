package com.gft.receitas.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@ManyToOne
	@JoinColumn(name = "receitas_id")
	Receita receita;
	
	@ManyToOne
	@JoinColumn(name = "ingredientes_id")
	@NotNull(message="Escolha um ingrediente!")
	Ingrediente ingrediente;
	
	@ManyToOne
	@NotNull(message="Escolha uma unidade de medida!")
	UnidadeMedida unidadeMedida;
	
	@Min(value = 1, message = "Quantidade não deveria ser menor que 1!")
    @Max(value = 999, message = "Quantidade não deveria ser maior que 999!")
	@Positive(message = "Quantidade deve ser positivo!")
	int quantidade;
	
	public Item() {
	}

	public Item(Receita receita, Ingrediente ingrediente,
			UnidadeMedida unidadeMedida) {
		super();
		this.receita = receita;
		this.ingrediente = ingrediente;
		this.unidadeMedida = unidadeMedida;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
