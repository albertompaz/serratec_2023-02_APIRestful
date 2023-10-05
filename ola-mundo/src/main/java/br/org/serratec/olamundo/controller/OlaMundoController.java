package br.org.serratec.olamundo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/primeiraAula")
public class OlaMundoController {

	@GetMapping
	public String getOlaMundo() {
		return "Olá Mundo!";
	}
	

	@GetMapping("/boaNoite")
	public String getBoaNoite() {
		return "Boa Noite";
	}
	
	/*
	@RequestMapping("/ola")
	public String olaMundo() {
		return "Ola Mundo Pelo Request Mapping";
	}
	
	@RequestMapping(value="/mensagem", method=RequestMethod.GET, produces={"application/json"})
	public String msg() {
		return "Boa noite";
	}
	*/
	
}
