package michaelstudy.ecommercenewapi.cliente.controller.request;

import lombok.Value;
import michaelstudy.ecommercenewapi.usuario.Usuario;

@Value
public class UsuarioRequest {

	String login;
	String senha;

}