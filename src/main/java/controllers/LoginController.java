package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import config.security.RecoveryJwtTokenDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import models.Login;
import services.UsuarioService;

@RestController
@Tag(name = "Login", description = "Operações relacionadas a acesso ao sistema")
public class LoginController {

    @Autowired
    private UsuarioService service;

    @CrossOrigin(origins = "*")
    @PostMapping("/usuarios/login")
    public ResponseEntity<Object> authenticateUser(@RequestBody Login loginUserDto) {
        try {
        	System.out.println("cheguei aqui");
            RecoveryJwtTokenDto token = service.authenticateUser(loginUserDto);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    
}