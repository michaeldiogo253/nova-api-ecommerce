package michaelstudy.ecommercenewapi.produtos.controller;

import lombok.RequiredArgsConstructor;
import michaelstudy.ecommercenewapi.produtos.controller.response.ProdutoResponse;
import michaelstudy.ecommercenewapi.produtos.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
public class ListarTodosProdutosController {

	private final ProdutoRepository produtoRepository;

	@GetMapping("/listar-todos")
	public ResponseEntity<List<ProdutoResponse>> execute() {

		var produtos = produtoRepository.findAll();

		return ResponseEntity.ok(ProdutoResponse.from(produtos));

	}
}
