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

	// i % 3 trim()
	// resto 0 -> ingrediente
	// resto 1 -> unidade de Medida
	// resto 2 -> quantidade
	public Receita salvarReceita (Receita receita) {
		receitaRepository.save(receita);
		String[] partes = receita.getInfo().split(";");
		Item item = new Item();
		//Boolean primeiraVez = true;

		for(int i = 0; i < partes.length; i++) {
			if (i%3 == 0) {
				
				item.setReceita(receita);
				
				if (ingredienteRepository.findByNome(partes[i]) != null) {
					item.setIngrediente(ingredienteRepository.findByNome(partes[i]));
				} else {
					Ingrediente ingrediente = new Ingrediente();
					ingrediente.setNome(partes[i]);
					ingredienteRepository.save(ingrediente);
					item.setIngrediente(ingrediente);		
				}
			} else if (i%3 == 1) {
				if(unidadeMedidaRepository.findByNome(partes[i]) != null) {
					item.setUnidadeMedida(unidadeMedidaRepository.findByNome(partes[i]));
				} else {
					UnidadeMedida unidadeMedida = new UnidadeMedida();
					unidadeMedida.setNome((partes[i]));
					unidadeMedidaRepository.save(unidadeMedida);
					item.setUnidadeMedida(unidadeMedida);
				}
			} else if (i%3 == 2) {
				int numero = Integer.parseInt(partes[i]);
				item.setQuantidade(numero);
				itemRepository.save(item);
				receitaRepository.save(receita);
				item = new Item();
			}
		}
		
		return null;
	}
	
	public Receita obterReceita (Long id) throws Exception {
		Optional<Receita> receita = receitaRepository.findById(id);
		
		if(receita.isEmpty()) {
			throw new Exception ("Receita não encontrada!");
		}
		
		return receita.get();
	}
	
	public List<Receita> listaReceita (String nome) {
		if(nome != null) {
			return receitaRepository.findByNomeContains(nome);
		}
		
		return listaReceitaCompleta();
	}
		
	public List<Receita> listaReceitaCompleta() {
		return receitaRepository.findAll();
	}
	
	public void excluirReceita (Long id) {
		receitaRepository.deleteById(id);
		itemRepository.deleteById(id);
	}
	
	public void popularBanco() {
		System.out.println();
		Receita receita = new Receita();
		
		receita.setModoDePreparo("Adiciona o Gin e está Pronto");
		receita.setNome("GinLoco");
		receita.setTempoDePreparo(2);
		receita.setAlcoolico(true);
		receita.setInfo("Gin;Dose;3;Coca;MiliLitros;150;Vodka;Dose;1");
		
		salvarReceita(receita);

		
	}
	
}
