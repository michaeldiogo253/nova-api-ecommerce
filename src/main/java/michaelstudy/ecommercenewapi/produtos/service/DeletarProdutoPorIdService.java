package michaelstudy.ecommercenewapi.produtos.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import michaelstudy.ecommercenewapi.produtos.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Transactional
@RequiredArgsConstructor
@Service
public class DeletarProdutoPorIdService {

	private final ProdutoRepository produtoRepository;

	public void execute(Long idProduto) {

		if (produtoRepository.existsById(idProduto)) {
			throw new IllegalArgumentException("Não foi encontrado nenhum Produto com id " +
			                                   idProduto);
		}

		produtoRepository.deleteById(idProduto);

	}

}
