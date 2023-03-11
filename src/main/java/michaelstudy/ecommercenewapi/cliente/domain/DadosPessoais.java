package michaelstudy.ecommercenewapi.cliente.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import michaelstudy.ecommercenewapi.usuario.Usuario;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class DadosPessoais {

	private String nome;
	@Column(unique = true) private String cpf;
	private String telefone;
	private LocalDate dataNasc;

	@OneToOne(cascade = CascadeType.ALL) private Usuario usuario;

	public DadosPessoais(String nome, String cpf, String telefone, LocalDate dataNasc) {

		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.dataNasc = dataNasc;
	}
}