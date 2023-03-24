package michaelstudy.ecommercenewapi.usuario;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Usuario implements UserDetails {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id", nullable = false) private Long id;
	@Column(unique = true) private String login;
	private String senha;

	@ManyToMany(fetch = FetchType.EAGER) private List<Perfil> perfis = new ArrayList<>();

	public Usuario(String login, String senha) {

		this.login = login;
		this.senha = senha;
	}

	public Usuario(String login, String senha, List<Perfil> perfis) {

		this.login = login;
		this.senha = new BCryptPasswordEncoder().encode(senha);
		this.perfis = perfis;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return perfis;
	}

	@Override
	public String getPassword() {

		return senha;
	}

	@Override
	public String getUsername() {

		return login;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}
}
