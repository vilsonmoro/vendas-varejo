package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import models.Cliente;
import services.ClienteService;

@RestController
@Tag(name = "Cliente", description = "Operações relacionadas a clientes")
public class ClienteController {
	@Autowired
	private ClienteService service;

	@GetMapping("/clientes")
	public ResponseEntity<List<Cliente>> getAllClientes() {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}

	@GetMapping("/clientes/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
		Cliente cliente = service.findById(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente);
	}

	@PostMapping("/clientes")
	public ResponseEntity<Object> addCliente(@RequestBody Cliente cliente) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(service.addCliente(cliente));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("/clientes/{id}")
	public ResponseEntity<Object> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
		try {
			Cliente updateCliente = service.update(id, cliente);
			return ResponseEntity.ok(updateCliente);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/clientes/buscar")
	@Operation(summary = "Busca dados de clientes baseado em parâmetros")
	public ResponseEntity<List<Cliente>> buscarClientes(@RequestParam(required = false) Long id,
			@RequestParam(required = false) String nome) {
		List<Cliente> clientes = service.buscarClientes(id, nome);
		return ResponseEntity.ok(clientes);
	}
}