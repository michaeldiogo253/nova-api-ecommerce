package michaelstudy.ecommercenewapi.usuario;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Getter
@NoArgsConstructor
public class Perfil implements GrantedAuthority {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(nullable = false) private Long id;
	@Column(unique = true) private String nome;

	public Perfil(String nome) {

		this.nome = nome;
	}

	@Override
	public String getAuthority() {

		return this.nome;
	}
}
