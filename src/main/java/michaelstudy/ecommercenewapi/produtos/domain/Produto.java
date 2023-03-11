package michaelstudy.ecommercenewapi.produtos.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Table(name = "produtos")
@Entity
public class Produto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id", nullable = false) private Long id;

	@Column(unique = true, nullable = false) private String nome;
	private String descricao;
	private BigDecimal preco;
	private LocalDate dataCadastro = LocalDate.now();

	@Enumerated(EnumType.STRING) private Categoria categoria;

	public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {

		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
	}

	public String getNomeCategoria() {

		return this.categoria.toString();
	}

	public void atualizar(String nome, String descricao, BigDecimal preco, Categoria categoria) {

		this.nome = nome.isBlank() ? this.nome : nome;
		this.descricao = descricao.isBlank() ? this.descricao : nome;
		this.preco = preco == null ? this.preco : preco;
		this.categoria = categoria == null ? this.categoria : categoria;
	}
}
