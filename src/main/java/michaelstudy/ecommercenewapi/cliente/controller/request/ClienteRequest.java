package michaelstudy.ecommercenewapi.cliente.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {

	@NotBlank(message = "Campo nome é obrigatorio") private String nome;

	@NotBlank(message = "Campo cpf é obrigatorio") @CPF(message = "O CPF está no formato inválido.") private String cpf;

	@NotBlank(message = "Campo telefone é obrigatorio") private String telefone;

	@NotBlank(message = "Campo dataNascimento é obrigatorio") private String dataNasc;

	@NotNull(message = "Usuario Request é obrigatorio!") private UsuarioRequest usuarioRequest;

	@NotNull(message = "Endereco é obrigatorio") EnderecoRequest enderecoRequest;


	public String getCpf() {

		return this.cpf.replace(" ", "")
		               .replace("-", "")
		               .replace(".", "");
	}

}