package config.authentication;

import java.util.List;

public class PublicEndpoints {
	public static final List<String> ENDPOINTS = List.of(
	        "/h2-console/**",
	        "/css/**",
	        "/js/**",
	        "/images/**",
	        "/favicon.ico",
	        "/clientes/**",
	        "/formapagamento",
	        "/tipoentrega",
	        "/usuarios/**",
	        "/swagger-ui/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/endereco/**"
	    );
}
