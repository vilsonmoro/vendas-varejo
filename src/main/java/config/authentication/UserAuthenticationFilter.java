package config.authentication;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import config.userdetails.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Usuario;
import repositories.UsuarioRepository;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
   private final UsuarioRepository userRepository;
    private final List<AntPathRequestMatcher> publicMatchers;

    public UserAuthenticationFilter(JwtTokenService jwtTokenService, UsuarioRepository userRepository) {
        this.jwtTokenService = jwtTokenService;
        this.userRepository = userRepository;
        this.publicMatchers = PublicEndpoints.ENDPOINTS.stream()
                .map(AntPathRequestMatcher::new)
                .toList();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
            throws ServletException, IOException {
    	System.out.println("Requested Path: " + request.getRequestURI()); 
    	
        if (isPublicPath(request)) {
            filterChain.doFilter(request, response);
            return;
        }

       String token = recoveryToken(request);

        if (token != null && jwtTokenService.validarToken(token)) {
            String subject = jwtTokenService.getAssuntoToken(token);
            Usuario user = userRepository.findByUsuario(subject)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

            UserDetailsImpl userDetails = new UserDetailsImpl(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private boolean isPublicPath(HttpServletRequest request) {
    	return publicMatchers.stream().anyMatch(matcher -> matcher.matches(request));
    }

    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return null;
        }
        return authorizationHeader.substring(7);
    }
}
