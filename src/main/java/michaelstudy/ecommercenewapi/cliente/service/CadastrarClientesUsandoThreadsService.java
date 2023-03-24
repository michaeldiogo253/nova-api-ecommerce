package michaelstudy.ecommercenewapi.cliente.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import michaelstudy.ecommercenewapi.cliente.controller.request.ClienteRequest;
import michaelstudy.ecommercenewapi.usuario.repository.ClienteRepository;
import michaelstudy.ecommercenewapi.usuario.repository.PerfilRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@RequiredArgsConstructor
@Transactional
@Service
public class CadastrarClientesUsandoThreadsService {

	private final ClienteRepository clienteRepository;
	private final PerfilRepository perfilRepository;

	public void execute(List<ClienteRequest> requests) throws InterruptedException, ExecutionException {

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


		int poolSize = 2;
		ExecutorService service = Executors.newFixedThreadPool(poolSize);
		List<Future<Runnable>> futures = new ArrayList<Future<Runnable>>();


		CountDownLatch latch = new CountDownLatch(1);
		CadastrarClienteComThreadsComponent worker1 = new CadastrarClienteComThreadsComponent(latch,
		                                                                                      clienteRepository,
		                                                                                      perfilRepository,
		                                                                                      firstList);

		CadastrarClienteComThreadsComponent worker2 = new CadastrarClienteComThreadsComponent(latch,
		                                                                                      clienteRepository,
		                                                                                      perfilRepository,
		                                                                                      secondList);

		for (int n=0; n<2; n++) {

			if(n == 0 ){
				Future f1 = service.submit(worker1);
				futures.add(f1);

			}

			if(n == 1 ){

				Future f2 = service.submit(worker2);
				futures.add(f2);
			}

		}

		for (Future<Runnable> f : futures)
		{
			f.get();
		}

		service.shutdownNow();

		System.out.println("Fim da execucao dos dois threads no Use case Principal");

	}

}
