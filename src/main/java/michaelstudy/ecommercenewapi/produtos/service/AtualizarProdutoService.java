package michaelstudy.ecommercenewapi.produtos.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import michaelstudy.ecommercenewapi.produtos.controller.request.AtualizarProdutoRequest;
import michaelstudy.ecommercenewapi.produtos.domain.Produto;
import michaelstudy.ecommercenewapi.produtos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AtualizarProdutoService {

	private final ProdutoRepository produtoRepository;

	public void execute(AtualizarProdutoRequest request) {

		Produto produto = produtoRepository.findById(request.idProduto())
		                                   .orElseThrow(() -> new IllegalArgumentException("Produto nao encontrado"));

		produto.atualizar(request.nome(), request.descricao(), request.preco(), request.categoria());

		produtoRepository.save(produto);
	}

}
