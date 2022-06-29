package com.gft.receitas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.receitas.entities.Ingrediente;
import com.gft.receitas.entities.Item;
import com.gft.receitas.entities.Receita;
import com.gft.receitas.entities.UnidadeMedida;
import com.gft.receitas.repositories.IngredienteRepository;
import com.gft.receitas.repositories.ItemRepository;
import com.gft.receitas.repositories.ReceitaRepository;
import com.gft.receitas.repositories.UnidadeMedidaRepository;

@Service
public class ReceitaService {

	@Autowired
	private ReceitaRepository receitaRepository;
	
	@Autowired
	private IngredienteRepository ingredienteRepository;

	@Autowired
	private UnidadeMedidaRepository unidadeMedidaRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	
	public Receita salvarReceita (Receita receita) {
		return receitaRepository.save(receita);
	}
	
	public Receita obterReceita (Long id) throws Exception {
		Optional<Receita> receita = receitaRepository.findById(id);
		
		if(receita.isEmpty()) {
			throw new Exception ("Receita não encontrada!");
		}
		
		return receita.get();
	}
	
	public List<Receita> listaReceita () {
		return receitaRepository.findAll();
	}
	
	public void excluirReceita (Long id) {
		receitaRepository.deleteById(id);
		itemRepository.deleteById(id);
	}
	
	public void popularBanco() {
		Receita receita = new Receita();
		Ingrediente ingrediente = new Ingrediente();
		Item item = new Item();
		UnidadeMedida unidadeMedida = new UnidadeMedida();
		receita.setModoDePreparo("Adiciona o Gin e está Pronto");
		receita.setNome("GinLoco");
		receita.setTempoDePreparo(2);
		ingrediente.setNome("Gin");
		unidadeMedida.setNome("Dose");
		item.setUnidadeMedida(unidadeMedida);
		item.setIngrediente(ingrediente);
		item.setReceita(receita);
		receitaRepository.save(receita);
		ingredienteRepository.save(ingrediente);
		unidadeMedidaRepository.save(unidadeMedida);
		itemRepository.save(item);
	}
	
}
