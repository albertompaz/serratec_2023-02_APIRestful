package br.org.serratec.olamundo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculadora")
public class CalculadoraController {

	@GetMapping("/somar")
	public String somar(@RequestParam(defaultValue = "0") Integer num1, @RequestParam(defaultValue = "0") Integer num2) {
		return "A soma dos numeros é: " + (num1 + num2);
	}
	
	@GetMapping("/subtrair")
	public String subtrair(@RequestParam(defaultValue = "0") Integer num1, @RequestParam(defaultValue = "0") Integer num2) {
		return "A subtração dos numeros é: " + (num1 - num2);
	}
	
	@GetMapping("/multiplicar")
	public String multiplicar(@RequestParam(defaultValue = "0") Integer num1, @RequestParam(defaultValue = "0") Integer num2) {
		return "A soma dos numeros é: " + (num1 * num2);
	}
	
	@GetMapping("/dividir")
	public String dividir(@RequestParam(defaultValue = "0") Integer num1, @RequestParam(defaultValue = "0") Integer num2) {
		if (num2 == 0) {
			return "Não se pode dividir um número por zero";
		}
		return "A divisão dos numeros é: " + (num1 / num2);
	}
	
}
