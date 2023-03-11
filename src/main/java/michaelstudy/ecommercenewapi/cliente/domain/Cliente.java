package michaelstudy.ecommercenewapi.cliente.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import michaelstudy.ecommercenewapi.usuario.Usuario;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "clientes")
public class Cliente {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column( nullable = false) private Long id;

	@Embedded private DadosPessoais dadosPessoais;

	@Embedded private Endereco endereco;

	public Cliente(String nome,
	               String cpf,
	               String telefone,
	               LocalDate dataNasc,
	               Endereco endereco) {

		this.dadosPessoais = new DadosPessoais(nome, cpf, telefone, dataNasc);
		this.endereco = endereco;

	}

	public Cliente(DadosPessoais dadosPessoais, Endereco endereco) {

		this.dadosPessoais = dadosPessoais;
		this.endereco = endereco;

	}


}
