package com.gft.receitas.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gft.receitas.entities.Receita;
import com.gft.receitas.services.ReceitaService;

@Controller
@RequestMapping("/receita")
public class ReceitaController {

	@Autowired
	private ReceitaService receitaService;
	
	@RequestMapping(path = "/manage")
	public ModelAndView editarReceita(@RequestParam (required=false) Long id) {
		ModelAndView mv = new ModelAndView("receita/form");
		Receita receita;
		
		if(id==null) {
			receita = new Receita();
		} else {
			try {
				receita = receitaService.obterReceita(id);
			} catch (Exception e) {
				receita = new Receita();
				mv.addObject("mensagem", e.getMessage());
			}
		}
		
		mv.addObject("receita", receita);
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/manage")
	public ModelAndView salvarReceita(@Valid Receita receita, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("receita/form");
		
		boolean novo = true;
		
		if(receita.getId() != null) {
			mv.addObject("receita", receita);
			novo = false;
		}
			
		if(bindingResult.hasErrors()) {
			mv.addObject("receita", receita);
		}
		
		receitaService.salvarReceita(receita);
		
		if(novo) {
			mv.addObject("receita", new Receita());
		} else {
			mv.addObject("receita", receita);
		}

		mv.addObject("mensagem", "Receita salva com sucesso!");
		
		return mv;
	}
 }
