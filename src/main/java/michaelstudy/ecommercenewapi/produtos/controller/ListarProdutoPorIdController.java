package michaelstudy.ecommercenewapi.produtos.controller;

import lombok.RequiredArgsConstructor;
import michaelstudy.ecommercenewapi.produtos.controller.response.ProdutoResponse;
import michaelstudy.ecommercenewapi.produtos.domain.Produto;
import michaelstudy.ecommercenewapi.produtos.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
public class ListarProdutoPorIdController {

	private final ProdutoRepository produtoRepository;

	@GetMapping("/listar-por-id/{idProduto}")
	public ResponseEntity<ProdutoResponse> execute(@PathVariable Long idProduto) {

		Produto produto = produtoRepository.findById(idProduto)
		                                   .orElseThrow(() -> new IllegalArgumentException(
				                                   "Não foi encontrado nenhum Produto com id " +
				                                   idProduto));

		return ResponseEntity.ok(ProdutoResponse.from(produto));

	}
}
