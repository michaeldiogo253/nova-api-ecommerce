package michaelstudy.ecommercenewapi.produtos.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import michaelstudy.ecommercenewapi.produtos.domain.Categoria;
import michaelstudy.ecommercenewapi.produtos.domain.Produto;

import java.math.BigDecimal;
public record CadastrarProdutoRequest(@NotBlank(message = "Nome do produto é obrigatorio") String nome,
                                      @NotBlank(message = "Descrição do produto é obrigatoria") String descricao,
                                      @NotNull(message = "Preço do produto é obrigatorio") BigDecimal preco,
                                      @NotNull(message = "Categoria do produto é obrigatorio") Categoria categoria) {

	public Produto toModel() {

		return new Produto(this.nome, this.descricao(), this.preco(), categoria);
	}

}
