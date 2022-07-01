package com.gft.receitas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gft.receitas.services.ReceitaService;

@Controller
public class IndexController {

	@Autowired
	ReceitaService receitaService;
		
	@RequestMapping
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("redirect:/ingrediente");
		
		return mv;
	}
}
