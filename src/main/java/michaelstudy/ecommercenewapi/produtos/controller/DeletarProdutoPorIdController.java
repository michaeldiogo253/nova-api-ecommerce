package michaelstudy.ecommercenewapi.produtos.controller;

import lombok.RequiredArgsConstructor;
import michaelstudy.ecommercenewapi.produtos.service.DeletarProdutoPorIdService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
public class DeletarProdutoPorIdController {

	private final DeletarProdutoPorIdService deletarProdutoPorIdService;

	@DeleteMapping("/deletar-por-id/{idProduto}")
	public ResponseEntity.BodyBuilder execute(@PathVariable Long idProduto) {

		deletarProdutoPorIdService.execute(idProduto);

		return ResponseEntity.status(HttpStatus.NO_CONTENT);

	}

}
