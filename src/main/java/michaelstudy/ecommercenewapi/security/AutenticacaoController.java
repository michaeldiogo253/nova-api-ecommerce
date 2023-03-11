package michaelstudy.ecommercenewapi.security;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import michaelstudy.ecommercenewapi.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AutenticacaoController {

	private final AuthenticationManager authenticationManager;

	private final TokenService service;

	@PostMapping()
	public ResponseEntity<TokenJWTResponse> efetuarLogin(@RequestBody @Valid LoginRequest request) {

		var authenticationToken = new UsernamePasswordAuthenticationToken(request.login(), request.senha());

		Authentication authentication = authenticationManager.authenticate(authenticationToken);

		String tokenJWT = service.gerarToken((Usuario) authentication.getPrincipal());

		return ResponseEntity.ok(new TokenJWTResponse(tokenJWT));
	}

}
