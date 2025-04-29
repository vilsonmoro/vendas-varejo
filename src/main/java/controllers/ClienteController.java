package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import models.Cliente;
import services.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
    	System.out.println("cheguei aqui");
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Cliente cliente = service.findById(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }
    
    @PostMapping
    public ResponseEntity<Cliente> addCliente(@RequestBody Cliente cliente){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addCliente(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        try {
            Cliente updateCliente = service.update(id, cliente);
            return ResponseEntity.ok(updateCliente);
        } catch (RuntimeException e){
            e.printStackTrace(); // imprime no console o motivo real
            Cliente erroCliente = new Cliente();
            erroCliente.setNome("Erro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroCliente);
            //return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Cliente>> buscarClientes(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String nome) {
        List<Cliente> clientes = service.buscarClientes(id, nome);
        return ResponseEntity.ok(clientes);
    }
}