package michaelstudy.ecommercenewapi.cliente.service;

import jakarta.transaction.Transactional;
import michaelstudy.ecommercenewapi.cliente.controller.request.ClienteRequest;
import michaelstudy.ecommercenewapi.cliente.controller.request.EnderecoRequest;
import michaelstudy.ecommercenewapi.cliente.domain.Cliente;
import michaelstudy.ecommercenewapi.cliente.domain.DadosPessoais;
import michaelstudy.ecommercenewapi.cliente.domain.Endereco;
import michaelstudy.ecommercenewapi.usuario.Perfil;
import michaelstudy.ecommercenewapi.usuario.Usuario;
import michaelstudy.ecommercenewapi.usuario.repository.ClienteRepository;
import michaelstudy.ecommercenewapi.usuario.repository.PerfilRepository;
import michaelstudy.ecommercenewapi.usuario.repository.UsuarioRepository;
import michaelstudy.ecommercenewapi.util.ConversorDeDatas;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;


@Service
@Transactional
public class CadastrarClienteComThreadsComponent extends Thread {

	private ClienteRepository clienteRepository;
	private CountDownLatch latch;
	private PerfilRepository perfilRepository;

	private List<ClienteRequest> clientesRequests = new ArrayList<>();

	public CadastrarClienteComThreadsComponent(CountDownLatch latch,
	                                           ClienteRepository clienteRepository,
	                                           PerfilRepository perfilRepository,
	                                           List<ClienteRequest> clientesRequests) {

		this.latch = latch;
		this.clienteRepository = clienteRepository;
		this.perfilRepository = perfilRepository;
		this.clientesRequests = clientesRequests;
	}

	@Override
	public void run() {

		try {
			if(clientesRequests.isEmpty()){
				return;
			}

			latch.countDown();
			System.out.printf("[ %s ] created, blocked by the latch...\n", getName());
			latch.await();
			System.out.printf("[ %s ] starts at: %s\n", getName(), Instant.now());


			Perfil perfil = perfilRepository.findByNome("CLIENTE");

			List<Usuario> usuariosParaSalvar = new ArrayList<>();
			List<Cliente> clientesParaSalvar = new ArrayList<>();

			clientesRequests.forEach(request -> {

				Usuario usuario = new Usuario(request.getUsuarioRequest()
				                                     .getLogin(),
				                              request.getUsuarioRequest()
				                                     .getSenha(),
				                              List.of(perfil));
				usuariosParaSalvar.add(usuario);

				Endereco endereco = criaEndereco(request.getEnderecoRequest());

				DadosPessoais dadosPessoais = DadosPessoais.builder()
				                                           .cpf(request.getCpf())
				                                           .nome(request.getNome())
				                                           .dataNasc(ConversorDeDatas.converteToLocalDate(request.getDataNasc()))
				                                           .telefone(request.getTelefone())
				                                           .usuario(usuario)
				                                           .build();

				clientesParaSalvar.add(new Cliente(dadosPessoais, endereco));

			});

			clienteRepository.saveAllAndFlush(clientesParaSalvar);

			System.out.println("Fim da execucao do thread "+ getName() + " no momento: "+ Instant.now());

		} catch (InterruptedException e) {
			// handle exception
		}

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

}
