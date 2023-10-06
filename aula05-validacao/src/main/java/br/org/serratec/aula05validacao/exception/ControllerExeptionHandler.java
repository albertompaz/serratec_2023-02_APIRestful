package br.org.serratec.aula05validacao.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
/*
 * @ControllerAdvice -> anotação que fara meu spring enchergar minha classe como um Bean (objeto geranciado
 * pelo proprio Spring) e que essa classe agora será responsável por interceptar todas as exceções 
 * lançadas no meu sistema.
 * */

/*
 * ResponseEntityExceptionHandler -> Classe do Spring que ja possui vários metodos que usaremos para 
 * nos auxilia a tratar as nossas exceções. A partir dela que conseguimos alterar o nosso objeto de resposta
 * */
public class ControllerExeptionHandler extends ResponseEntityExceptionHandler {

	/*
	 * handleMethodArgumentNotValid -> Método herdado da classe ResponseEntityExceptionHandler que usaremos 
	 * para alterar o response da nossa API quando houver uma exceção lançada no sistema
	 * */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<String> erros = new ArrayList<>();
		/*
		 * Preenchendo minhas lista de erros a partir de uma lista de FieldError (classe do Spring).
		 * a classe FieldError contem cada erro que foi cometido pelo usuario perante as nossas validações 
		 * colocadas na nossa classe de entidade/modelo.
		 * */
		for(FieldError e: ex.getBindingResult().getFieldErrors()) {
			erros.add(e.getField() + ": " + e.getDefaultMessage());
		}
		
		ErroResposta erroResposta = new ErroResposta(status.value(), "Existem Campos Inválidos. Confira o preenchimento", LocalDateTime.now(), erros);
		
		/*
		 * handleExceptionInternal -> utilizamos esse metodo para criarmos efetivamente o nosso response.
		 * A unica propriedade que criamos para se passar nesse método é o erroResposta, que sera efetivamente 
		 * o nosso Body do response. Todos os outros parametros nós obtivemos através do método handleMethodArgumentNotValid
		 * que estamos sobrescrevendo.
		 * */
		return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
	}
	
}
