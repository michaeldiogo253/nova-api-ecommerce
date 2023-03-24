package michaelstudy.ecommercenewapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

	private final SecurityFilter securityFilter;
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		return httpSecurity.csrf()
		                   .disable()
		                   .sessionManagement()
		                   .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		                   .and()
		                   .authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.POST, "/login/**")
		                                                                .permitAll()
		                                                                .requestMatchers("/admin/**")
		                                                                .hasRole("ADMIN")
		                                                                .requestMatchers(HttpMethod.POST,
		                                                                                             "/cliente/cadastrar/**")
		                                                                .permitAll()
		                                                                .requestMatchers(HttpMethod.POST,
		                                                                                 "/clientes/cadastrar-varios-usando-threads")
		                                                                .permitAll()
		                                                                .requestMatchers(HttpMethod.DELETE,
		                                                                                 "/deletar-clientes")
		                                                                .permitAll()
		                                                                .requestMatchers(HttpMethod.POST,
		                                                                                 "/clientes/cadastrar-multiplos")
		                                                                .permitAll()
		                                                                .anyRequest()
		                                                                .authenticated())
		                   .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
		                   .build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws
	                                                                                                            Exception {

		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
		           .authenticationProvider(authProvider())
		           .build();
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
}
