package michaelstudy.ecommercenewapi.usuario.repository;

import michaelstudy.ecommercenewapi.usuario.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

	@Query("select p from Perfil p where p.nome = ?1")
	Perfil findByNome(String nome);


}
