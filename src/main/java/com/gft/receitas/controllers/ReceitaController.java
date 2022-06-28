package com.gft.receitas.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.receitas.entities.Receita;
import com.gft.receitas.entities.Item;
import com.gft.receitas.services.IngredienteService;
import com.gft.receitas.services.ReceitaService;
import com.gft.receitas.services.ItemService;
import com.gft.receitas.services.UnidadeMedidaService;

@Controller
@RequestMapping("/receita")
public class ReceitaController {

	@Autowired
	private ReceitaService receitaService;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private UnidadeMedidaService unidadeMedidaService;
	
	@Autowired
	private ItemService itemService; 
	
	@RequestMapping(path = "/manage")
	public ModelAndView editarReceita(@RequestParam (required=false) Long idItem, @RequestParam (required=false) Long idReceita) {
		ModelAndView mv = new ModelAndView("receita/form");
		Receita receita;
		Item item;
		
		if(idItem==null || idReceita==null) {
			receita = new Receita();
			item = new Item();
			mv.addObject("listaIngrediente", ingredienteService.listaIngredienteCompleto());
			mv.addObject("listaUnidadeMedida", unidadeMedidaService.listaUnidadeMedidaCompleto());
		} else {
			try {
				receita = receitaService.obterReceita(idReceita);
				item = itemService.obterItem(idItem);
			} catch (Exception e) {
				receita = new Receita();
				item = new Item();
				mv.addObject("mensagem", e.getMessage());
			}
		}
		
		item.setReceita(receita);
		mv.addObject("receita", receita);
		mv.addObject("item", item);
		mv.addObject("listaIngrediente", ingredienteService.listaIngredienteCompleto());
		mv.addObject("listaUnidadeMedida", unidadeMedidaService.listaUnidadeMedidaCompleto());
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/manage")
	public ModelAndView salvarReceita(@Valid Receita receita, Item item, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("receita/form");
		
		boolean novo = true;
		
		if(receita.getId() != null) {
			mv.addObject("receita", receita);
			mv.addObject("item", item);
			novo = false;
		}
			
		if(bindingResult.hasErrors()) {
			mv.addObject("receita", receita);
			mv.addObject("item", item);
		}
		
		receitaService.salvarReceita(receita);
		itemService.salvarReceitaNoItem(item, receita);
		itemService.salvarItem(item);

		if(novo) {
			mv.addObject("receita", new Receita());
			mv.addObject("item", new Item());
		} else {
			mv.addObject("receita", receita);
			mv.addObject("item", item);
		}
		
		mv.addObject("listaIngrediente", ingredienteService.listaIngredienteCompleto());
		mv.addObject("listaUnidadeMedida", unidadeMedidaService.listaUnidadeMedidaCompleto());
		mv.addObject("mensagem", "Receita salva com sucesso!");
		return mv;
	}
	
	@RequestMapping(path = "/popular")
	public ModelAndView popularBanco(@RequestParam Long id) {
		ModelAndView mv = new ModelAndView("redirect:/ingrediente");
		
		receitaService.popularBanco(id);
		
		return mv;
	}
	
	@RequestMapping
	public ModelAndView listarReceita() {
		ModelAndView mv = new ModelAndView("receita/listar");
		mv.addObject("listaItem", itemService.listaItens());
		mv.addObject("lista", receitaService.listaReceita());
		return mv;
	}
	
	@RequestMapping(path="/excluir")
	public ModelAndView excluirReceita(@RequestParam Long idItem, @RequestParam Long idReceita, RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("redirect:/receita");
		
		try {
			itemService.excluirItem(idItem);
			receitaService.excluirReceita(idReceita);
			redirectAttributes.addFlashAttribute("mensagem", "Drink excluído com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir drink! " + e.getMessage());
		}
		
		return mv;
	}
 }
