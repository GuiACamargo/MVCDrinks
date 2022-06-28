package com.gft.receitas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.receitas.entities.Receita;
import com.gft.receitas.entities.Item;
import com.gft.receitas.repositories.ItemRepository;

@Service
public class ItemService {

	@Autowired
	ItemRepository itemRepository;
	
	public Item salvarUnidadeIngreReceita(Item item) {
		return itemRepository.save(item);
	}
	
	public Item obterUnidadeIngreReceita (Long id) throws Exception {
		Optional<Item> item = itemRepository.findById(id);
		
		if(item.isEmpty()) {
			throw new Exception ("Conjunto não encontrada!");
		}
		
		return item.get();
	}
	
	public List<Item> listaUnidadeIngreReceita () {
		return itemRepository.findAll();
	}
	
	public void excluirUnidadeIngreReceita (Long id) {
		itemRepository.deleteById(id);
	}
	
	public Long acharId (Long id) {
		return itemRepository.findByIdContains(id);
	}
	
	public void salvarReceitaNoConjunto (Item item, Receita receita) {
		item.setReceita(receita);
	}
}
