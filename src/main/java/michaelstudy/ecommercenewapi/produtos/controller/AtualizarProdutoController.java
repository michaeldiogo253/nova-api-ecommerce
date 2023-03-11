package michaelstudy.ecommercenewapi.produtos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import michaelstudy.ecommercenewapi.produtos.controller.request.AtualizarProdutoRequest;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
public class AtualizarProdutoController {

	@PutMapping("/atualizar")
	public void execute(@RequestBody @Valid AtualizarProdutoRequest request ){



	}

}
