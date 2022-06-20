package com.gft.receitas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.receitas.repositories.ReceitaRepository;

@Service
public class ReceitaService {

	@Autowired
	private ReceitaRepository receitaRepository;
	
}
