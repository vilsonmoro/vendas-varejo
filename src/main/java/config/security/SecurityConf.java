package config.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import config.authentication.PublicEndpoints;
import config.authentication.UserAuthenticationFilter;

@Configuration
public class SecurityConf {

	@Autowired
	private UserAuthenticationFilter userAuthenticationFilter;

	@Autowired
	public SecurityConf(UserAuthenticationFilter userAuthenticationFilter) {
		this.userAuthenticationFilter = userAuthenticationFilter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		.cors(Customizer.withDefaults())
		.csrf(AbstractHttpConfigurer::disable)
		.headers(headers -> headers.frameOptions().disable())
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(PublicEndpoints.ENDPOINTS.toArray(new String[0]))
						.permitAll()
				        .anyRequest().authenticated())
				.addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
		/*httpSecurity
        .cors().and()
        .csrf().disable()
        .headers().frameOptions().disable()
        .and()
        .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeHttpRequests(auth -> auth
            // Libera os endpoints públicos definidos
            .requestMatchers(PublicEndpoints.ENDPOINTS.toArray(new String[0])).permitAll()
            
            // Libera o acesso para usuários autenticados com ROLE_USER
            .requestMatchers("/clientes/**").hasRole("USER")

            // Qualquer outra requisição precisa de autenticação
            .anyRequest().authenticated()
        )
        // Adiciona o filtro JWT antes do filtro padrão de login
        .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return httpSecurity.build();*/
		
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	 @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration config = new CorsConfiguration();
	        config.setAllowedOrigins(List.of("*"));  
	        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	        config.setAllowedHeaders(List.of("*"));

	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", config);  
	        return source;
	    }
	
}