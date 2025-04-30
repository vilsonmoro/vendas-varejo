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
	        "/usuarios/**"
	    );
}
