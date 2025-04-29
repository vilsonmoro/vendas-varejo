package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.Cliente;
import models.Endereco;
import models.Usuario;
import repositories.ClienteRepository;
import repositories.EnderecoRepository;
import repositories.UsuarioRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Cliente> findAll(){
        return repository.findAll();
    }

    public Cliente findById(Long id){
        return repository.findById(id).get();
    }

    public Cliente addCliente(Cliente cliente){
        if (cliente.getUsuario() != null && cliente.getUsuario().getId() != null) {
            Usuario usuario = usuarioRepository.findById(cliente.getUsuario().getId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            cliente.setUsuario(usuario);
        }

        if (cliente.getEndereco() != null && cliente.getEndereco().getId() != null) {
            Endereco endereco = enderecoRepository.findById(cliente.getEndereco().getId()).orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
            cliente.setEndereco(endereco);
        }

        return repository.save(cliente);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Cliente update(Long id, Cliente cliente){
        
        if (!repository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado");
        }
        cliente.setId(id);

        if (cliente.getUsuario() != null && cliente.getUsuario().getId() != null) {
            Usuario usuario = usuarioRepository.findById(cliente.getUsuario().getId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            cliente.setUsuario(usuario);
        }
        
        if (cliente.getEndereco() != null && cliente.getEndereco().getId() != null) {
            Endereco endereco = enderecoRepository.findById(cliente.getEndereco().getId()).orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
            cliente.setEndereco(endereco);
        }
        
        return repository.save(cliente);
    }

    public List<Cliente> buscarClientes(Long id, String nome) {
        return repository.buscarClientes(id, nome);
    }

}