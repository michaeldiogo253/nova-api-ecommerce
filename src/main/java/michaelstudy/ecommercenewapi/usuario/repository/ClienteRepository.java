package michaelstudy.ecommercenewapi.usuario.repository;

import michaelstudy.ecommercenewapi.cliente.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
