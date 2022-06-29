package com.gft.receitas.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.receitas.entities.Item;

interface ReceitaId{
	String getReceita();
}

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	Long findByIdContains(Long id);
	
	Collection<ReceitaId> findByReceita_id(Long id);
	
	List<Item> findByReceitaNome(String nome);
	
}
