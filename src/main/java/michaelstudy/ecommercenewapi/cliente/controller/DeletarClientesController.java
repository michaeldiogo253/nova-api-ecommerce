package michaelstudy.ecommercenewapi.cliente.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import michaelstudy.ecommercenewapi.usuario.repository.ClienteRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Transactional
public class DeletarClientesController {

	private final ClienteRepository clienteRepository;

	@DeleteMapping("/deletar-clientes")
	public void execute() {

		clienteRepository.deleteAll();

	}

}
