package michaelstudy.ecommercenewapi.cliente.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import michaelstudy.ecommercenewapi.cliente.controller.request.ClienteRequest;
import michaelstudy.ecommercenewapi.usuario.repository.ClienteRepository;
import michaelstudy.ecommercenewapi.usuario.repository.PerfilRepository;
import michaelstudy.ecommercenewapi.usuario.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@RequiredArgsConstructor
@Transactional
@Service
public class CadastrarClientesUsandoThreadsService {

	private final ClienteRepository clienteRepository;
	private final PerfilRepository perfilRepository;

	public void execute(List<ClienteRequest> requests) throws InterruptedException {

		if (requests.isEmpty()) {
			return;
		}


		int size = requests.size();

		List<ClienteRequest> firstList = new ArrayList<>(requests.subList(0,
		                                                                  (size +
		                                                                   1) /
		                                                                  2));
		List<ClienteRequest> secondList = new ArrayList<>(requests.subList((size +
		                                                                    1) /
		                                                                   2, size));

		CountDownLatch latch = new CountDownLatch(1);


		for (int n=0; n<2; n++) {

			if(n == 0 ){
				CadastrarClienteComThreadsComponent worker1 = new CadastrarClienteComThreadsComponent(latch,
				                                                                                      clienteRepository,
				                                                                                      perfilRepository,
				                                                                                      firstList);
				worker1.start();
			}
			if(n == 1 ){
				CadastrarClienteComThreadsComponent worker2 = new CadastrarClienteComThreadsComponent(latch,
				                                                                                      clienteRepository,
				                                                                                      perfilRepository,
				                                                                                      secondList);
				worker2.start();
			}

		}

		latch.await();

		System.out.println("Fim da execucao dos dois threads no Use case Principal");

	}

}
