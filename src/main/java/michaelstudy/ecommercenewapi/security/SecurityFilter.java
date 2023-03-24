package michaelstudy.ecommercenewapi.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import michaelstudy.ecommercenewapi.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

	private final TokenService tokenService;
	private final UsuarioRepository usuarioRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
	                                HttpServletResponse response,
	                                FilterChain filterChain) throws ServletException, IOException {

		var jwtToken = recuperarToken(request);

		if(jwtToken != null){
			var subject = tokenService.getSubject(jwtToken);
			UserDetails usuario = usuarioRepository.findByLogin(subject);

			var authentication =  new UsernamePasswordAuthenticationToken(usuario, usuario.getPassword(), usuario.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authentication);
		}


		filterChain.doFilter(request, response);

	}

	private String recuperarToken(HttpServletRequest request) {

		var autorizationHeader = request.getHeader("Authorization");

		if (autorizationHeader !=
		    null) {
			return autorizationHeader.replace("Bearer ", "");
		}

		return null;
	}
}
