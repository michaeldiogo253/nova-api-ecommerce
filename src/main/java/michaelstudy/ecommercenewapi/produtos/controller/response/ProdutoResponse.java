package michaelstudy.ecommercenewapi.produtos.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import michaelstudy.ecommercenewapi.produtos.domain.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


public record ProdutoResponse (Long id,
                               String nome,
                               String descricao,
                               BigDecimal preco,
                               String nomeCategoria){



	public static ProdutoResponse from(Produto produto) {

		return new ProdutoResponse(produto.getId(),
		                           produto.getNome(),
		                           produto.getDescricao(),
		                           produto.getPreco(),
		                           produto.getNomeCategoria());
	}

	public static List<ProdutoResponse> from(List<Produto> produtos) {

		return produtos.stream().map(ProdutoResponse::from).collect(Collectors.toList());
	}
}