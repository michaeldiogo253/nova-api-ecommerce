package michaelstudy.ecommercenewapi.util.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> trataErro404() {

		return ResponseEntity.notFound()
		                     .build();

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> trataErro400(MethodArgumentNotValidException ex) {

		var errors = ex.getFieldErrors();
		return ResponseEntity.badRequest()
		                     .body(errors.stream()
		                                 .map(ErroDeValidacaoResponse::new)
		                                 .toList());

	}

	private record ErroDeValidacaoResponse(String campo, String menssagem) {

		public ErroDeValidacaoResponse(FieldError fieldError) {

			this(fieldError.getField(), fieldError.getDefaultMessage());
		}
	}

}
