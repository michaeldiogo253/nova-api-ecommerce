package michaelstudy.ecommercenewapi.produtos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import michaelstudy.ecommercenewapi.produtos.controller.request.CadastrarProdutoRequest;
import michaelstudy.ecommercenewapi.produtos.controller.response.ProdutoResponse;
import michaelstudy.ecommercenewapi.produtos.domain.Produto;
import michaelstudy.ecommercenewapi.produtos.service.CadastrarProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
public class CadastrarProdutoController {

	private final CadastrarProdutoService cadastrarProdutoService;

	@PostMapping("/cadastrar")
	public ResponseEntity<ProdutoResponse> cadastrarProduto(@RequestBody @Valid CadastrarProdutoRequest request,
	                                                        UriComponentsBuilder uriBuilder) {

		Produto produtoCriado = cadastrarProdutoService.execute(request);

		URI uri = uriBuilder.path("ecommerce-api/produto/{id}")
		                    .buildAndExpand(produtoCriado.getId())
		                    .toUri();

		return ResponseEntity.created(uri)
		                     .body(ProdutoResponse.from(produtoCriado));
	}

}
