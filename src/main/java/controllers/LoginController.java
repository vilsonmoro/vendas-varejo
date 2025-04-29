package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import services.UsuarioService;

@RequestMapping("/login")
@RestController
public class LoginController {

    @Autowired
    private UsuarioService service;

   /* @PostMapping
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@RequestBody Login loginUserDto) {
        try {
            RecoveryJwtTokenDto token = service.authenticateUser(loginUserDto);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }*/
    
}