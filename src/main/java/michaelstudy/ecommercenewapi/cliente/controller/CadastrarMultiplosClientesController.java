package michaelstudy.ecommercenewapi.cliente.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import michaelstudy.ecommercenewapi.cliente.controller.request.ClienteRequest;
import michaelstudy.ecommercenewapi.cliente.service.CadastrarMultiplosClientesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CadastrarMultiplosClientesController {

	private final CadastrarMultiplosClientesService cadastrarMultiplosClientesService;

	@PostMapping("/clientes/cadastrar-multiplos")
	public ResponseEntity<Void> cadastrarCliente(@RequestBody @Valid List<ClienteRequest> requests) {

		cadastrarMultiplosClientesService.execute(requests);

		return ResponseEntity.ok()
		                     .build();

	}

}
