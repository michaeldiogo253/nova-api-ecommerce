package michaelstudy.ecommercenewapi.produtos.repository;

import michaelstudy.ecommercenewapi.produtos.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
