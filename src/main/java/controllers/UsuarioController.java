package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import models.Usuario;
import services.UsuarioService;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService service;
    
    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/usuarios")
    public ResponseEntity<Object> addUsuario(@RequestBody Usuario usuario){
        try {
        	usuario.setSenha(encoder.encode(usuario.getSenha()));
            return ResponseEntity.ok(service.addUsuario(usuario));
        }catch (Exception e) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
    
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            usuario.setSenha(encoder.encode(usuario.getSenha()));
            Usuario updateUsuario = service.update(id, usuario);
            return ResponseEntity.ok(updateUsuario);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuarios/buscar")
    public ResponseEntity<List<Usuario>> buscarUsuarios(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String sobrenome) {
        List<Usuario> usuarios = service.buscarUsuarios(id, nome, sobrenome);
        return ResponseEntity.ok(usuarios);
    }

}
