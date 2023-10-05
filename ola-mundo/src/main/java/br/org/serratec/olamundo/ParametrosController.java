package br.org.serratec.olamundo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parametros")
public class ParametrosController {

	@GetMapping
	public String textoEmCaixaAlta(@RequestParam String palavra1, @RequestParam(defaultValue = "0") Integer num1) {
		return palavra1.toUpperCase() + " " + num1;
	}

}
