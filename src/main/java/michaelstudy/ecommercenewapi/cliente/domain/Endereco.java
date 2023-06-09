package michaelstudy.ecommercenewapi.cliente.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Builder
public class Endereco {

	private String logradouro;
	private String numero;
	private String cidade;
	private String pais;
	private String cep;
	private String complemento;

	public void atualizaEndereco(Endereco novoEndereco) {

		this.logradouro = isaNullOrEmpty(novoEndereco.logradouro) ? this.logradouro : novoEndereco.logradouro;
		this.numero = isaNullOrEmpty(novoEndereco.numero) ? this.numero : novoEndereco.numero;
		this.cidade = isaNullOrEmpty(novoEndereco.cidade) ? this.cidade : novoEndereco.cidade;
		this.pais = isaNullOrEmpty(novoEndereco.pais) ? this.pais : novoEndereco.pais;
		this.cep = isaNullOrEmpty(novoEndereco.cep) ? this.cep : novoEndereco.cep;
		this.complemento = isaNullOrEmpty(novoEndereco.complemento) ? this.complemento : novoEndereco.complemento;
	}

	private boolean isaNullOrEmpty(String atributo) {

		return atributo == null || atributo.equals("");
	}

}