package com.gft.receitas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.receitas.entities.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

	List<Receita> findByNome(String nome);
}
