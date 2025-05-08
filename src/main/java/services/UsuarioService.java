package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import config.authentication.JwtTokenService;
import config.security.RecoveryJwtTokenDto;
import config.userdetails.UserDetailsImpl;
import models.Login;
import models.Usuario;
import repositories.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;
    
    @Autowired
    private  AuthenticationManager authenticationManager;
    
    @Autowired
    private  JwtTokenService jwtTokenService;

    public RecoveryJwtTokenDto authenticateUser(Login loginUserDto) {
       UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.getUsuario(), loginUserDto.getSenha());
               
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        
        return new RecoveryJwtTokenDto(jwtTokenService.gerarToken(userDetails));
    }

    public Usuario addUsuario(Usuario usuario){
        return repository.save(usuario);
    }

    public Usuario findById(long id){
        return repository.findById(id).get();
    }
    
    public Usuario findByUsuario(String usuario){
        return repository.findByUsuario(usuario).get();
    }

    public List<Usuario> findAll(){
        return repository.findAll();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Usuario update(Long id, Usuario usuario){
        if (!repository.existsById(id)) {
            throw new RuntimeException("Usuario não encontrado");}
        usuario.setId(id);
        return repository.save(usuario);
    }

    public List<Usuario> buscarUsuarios(Long id, String nome, String sobrenome) {
        return repository.buscarUsuarios(id, nome, sobrenome);
    }

}