package michaelstudy.ecommercenewapi.produtos.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import michaelstudy.ecommercenewapi.produtos.controller.request.CadastrarProdutoRequest;
import michaelstudy.ecommercenewapi.produtos.domain.Produto;
import michaelstudy.ecommercenewapi.produtos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CadastrarProdutoService {

	private final ProdutoRepository produtoRepository;

	public Produto execute(CadastrarProdutoRequest request){

		Produto produto = new Produto(request.nome(), request.descricao(), request.preco(), request.categoria());

		produtoRepository.save(produto);

		return produto;

	}

}
