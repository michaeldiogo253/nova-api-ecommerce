//package michaelstudy.ecommercenewapi.cliente.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import michaelstudy.ecommercenewapi.cliente.controller.request.ClienteRequest;
import michaelstudy.ecommercenewapi.cliente.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/*
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/cliente")
public class CadastrarClienteController {

	private final CadastrarClienteService useCase;

	@PostMapping("/cadastrar")
	public ResponseEntity<ClienteResponse> cadastrarCliente(@RequestBody @Valid ClienteRequest request,
	                                                        UriComponentsBuilder uriBuilder) {

		Cliente clienteCriado = useCase.execute(request.toModel());

		URI uri = uriBuilder.path("ecommerce-api/cliente/{id}")
		                    .buildAndExpand(clienteCriado.getId())
		                    .toUri();

		return ResponseEntity.created(uri).body(new ClienteResponse(clienteCriado));

	}

}
*/
