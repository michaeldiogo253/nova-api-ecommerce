package michaelstudy.ecommercenewapi.security;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank(message = "Login nao pode estar vazio") String login,
                           @NotBlank(message = "Senha nao pode estar vazio") String senha) {

}
