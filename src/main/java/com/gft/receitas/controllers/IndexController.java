package com.gft.receitas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.receitas.services.ReceitaService;

@Controller
public class IndexController {

	@Autowired
	ReceitaService receitaService;
	
	Boolean primeiraVez = true;
	
	@RequestMapping
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	
	@RequestMapping(path = "/popular")
	public ModelAndView popularBanco(RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("redirect:/");
		if (primeiraVez) {
			receitaService.popularBanco();	
			redirectAttributes.addFlashAttribute("mensagem", "Banco Populado com Sucesso!");
			primeiraVez = false;
		} else {
			redirectAttributes.addFlashAttribute("mensagem", "Banco já foi Populado!");
		}
		
		return mv;
	}
}
