package michaelstudy.ecommercenewapi.usuario.controller;

import lombok.RequiredArgsConstructor;
import michaelstudy.ecommercenewapi.usuario.Perfil;
import michaelstudy.ecommercenewapi.usuario.repository.PerfilRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CadastrarPerfilController {

	private final PerfilRepository perfilRepository;

	@PostMapping("/admin/cadastrar-perfil/{nomePerfil}")
	public ResponseEntity<Object> execute(@PathVariable String nomePerfil) {

		perfilRepository.save(new Perfil(nomePerfil.trim()
		                                           .toUpperCase()));

		return ResponseEntity.status(HttpStatus.CREATED)
		                     .build();
	}

}
