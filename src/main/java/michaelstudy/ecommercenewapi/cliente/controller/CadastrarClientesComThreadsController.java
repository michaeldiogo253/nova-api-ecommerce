package michaelstudy.ecommercenewapi.cliente.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import michaelstudy.ecommercenewapi.cliente.controller.request.ClienteRequest;
import michaelstudy.ecommercenewapi.cliente.service.CadastrarClientesUsandoThreadsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class CadastrarClientesComThreadsController {

	private final CadastrarClientesUsandoThreadsService useCase;

	@PostMapping("/clientes/cadastrar-varios-usando-threads")
	public ResponseEntity<Void> cadastrarCliente(@RequestBody @Valid List<ClienteRequest> requests) throws
	                                                                                                InterruptedException,
	                                                                                                ExecutionException {

		useCase.execute(requests);

		return ResponseEntity.ok()
		                     .build();

	}

}
