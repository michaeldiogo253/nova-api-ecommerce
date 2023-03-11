package michaelstudy.ecommercenewapi.cliente.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import michaelstudy.ecommercenewapi.cliente.domain.Endereco;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoRequest {

	@NotBlank(message = "Campo logradouro é obrigatorio") private String logradouro;
	@NotBlank(message = "Campo numero é obrigatorio") private String numero;
	@NotBlank(message = "Campo cidade é obrigatorio") private String cidade;
	@NotBlank(message = "Campo pais é obrigatorio") private String pais;
	@NotBlank(message = "Campo cep é obrigatorio") private String cep;
	@NotBlank(message = "Campo complemento é obrigatorio") private String complemento;

	public Endereco toModel() {

		return new Endereco(this.logradouro,
		                    this.numero,
		                    this.getCidade(),
		                    this.getPais(),
		                    this.cep,
		                    this.getComplemento());
	}
}