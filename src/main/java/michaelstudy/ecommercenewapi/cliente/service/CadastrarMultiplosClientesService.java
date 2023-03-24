package michaelstudy.ecommercenewapi.cliente.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import michaelstudy.ecommercenewapi.cliente.controller.request.ClienteRequest;
import michaelstudy.ecommercenewapi.cliente.controller.request.EnderecoRequest;
import michaelstudy.ecommercenewapi.cliente.domain.Cliente;
import michaelstudy.ecommercenewapi.cliente.domain.DadosPessoais;
import michaelstudy.ecommercenewapi.cliente.domain.Endereco;
import michaelstudy.ecommercenewapi.usuario.Perfil;
import michaelstudy.ecommercenewapi.usuario.Usuario;
import michaelstudy.ecommercenewapi.usuario.repository.ClienteRepository;
import michaelstudy.ecommercenewapi.usuario.repository.PerfilRepository;
import michaelstudy.ecommercenewapi.util.ConversorDeDatas;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class CadastrarMultiplosClientesService {

	private final PerfilRepository perfilRepository;

	private final ClienteRepository clienteRepository;

	public void execute(@Valid List<ClienteRequest> requests){

		List<Cliente> clientesParaSalvar = requests.stream()
		                                .map(this::criaCliente)
		                                .collect(Collectors.toList());

		clienteRepository.saveAll(clientesParaSalvar);

	}

	public Cliente criaCliente(ClienteRequest request) {

		DadosPessoais dadosPessoais = criaDadosPessoais(request);

		Endereco endereco = criaEndereco(request.getEnderecoRequest());

		return new Cliente(dadosPessoais, endereco);

	}

	private static Endereco criaEndereco(EnderecoRequest enderecoRequest) {

		return Endereco.builder()
		               .cep(enderecoRequest.getCep())
		               .pais(enderecoRequest.getPais())
		               .complemento(enderecoRequest.getComplemento())
		               .logradouro(enderecoRequest.getLogradouro())
		               .numero(enderecoRequest.getNumero())
		               .cidade(enderecoRequest.getCidade())
		               .build();
	}

	private DadosPessoais criaDadosPessoais(ClienteRequest request) {

		Perfil perfil = perfilRepository.findByNome("CLIENTE");

		Usuario usuario = new Usuario(request.getUsuarioRequest()
		                                     .getLogin(),
		                              request.getUsuarioRequest()
		                                     .getSenha(),
		                              List.of(perfil));

		return DadosPessoais.builder()
		                    .cpf(request.getCpf())
		                    .nome(request.getNome())
		                    .dataNasc(ConversorDeDatas.converteToLocalDate(request.getDataNasc()))
		                    .telefone(request.getTelefone())
		                    .usuario(usuario)
		                    .build();

	}
}
