/*
package michaelstudy.ecommercenewapi.cliente.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import michaelstudy.ecommercenewapi.cliente.domain.Cliente;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class CadastrarClienteService {

	private final PerfilRepository perfilRepository;
	private final UsuarioRepository usuarioRepository;

	@Override
	public Cliente execute(Cliente cliente) {

		Perfil perfil = perfilRepository.findByNome("ROLE_CLIENTE")
		                                .orElse(null);

		usuarioRepository.save(cliente.getUsuario());

		cliente.atribuiPerfil(perfil);

		return salvarClientePort.salvarCliente(cliente);
	}
}*/
