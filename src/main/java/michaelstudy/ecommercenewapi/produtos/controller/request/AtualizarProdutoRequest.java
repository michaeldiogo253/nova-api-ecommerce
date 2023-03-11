package michaelstudy.ecommercenewapi.produtos.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import michaelstudy.ecommercenewapi.produtos.domain.Categoria;

import java.math.BigDecimal;

public record AtualizarProdutoRequest(@NotNull(message = "Id produto nao pode ser nulo") Long idProduto, String nome,
                                      BigDecimal preco, String descricao, Categoria categoria) {

}
