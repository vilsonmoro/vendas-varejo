package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.TipoStatus;
import models.Vendedor;
import repositories.UsuarioRepository;
import repositories.VendedorRepository;

@Service
public class VendedorService {
    @Autowired
    private VendedorRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Vendedor addVendedor(Vendedor vendedor){
       /* if (((Usuario) vendedor).getUsuario() != null && vendedor.getUsuario().getId() != null) {
            Usuario usuario = usuarioRepository.findById(vendedor.getUsuario().getId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            vendedor.setUsuario(usuario);
        }*/
        return repository.save(vendedor);
    }

    public Vendedor findById(Long id){
        return repository.findById(id).get();
    }

    public List<Vendedor> findAll(){
        return repository.findAll();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Vendedor update(Long id, Vendedor vendedor){
        if (!repository.existsById(id)) {
            throw new RuntimeException("Vendedor não encontrado");}
        
       /* if (vendedor.getUsuario() != null && vendedor.getUsuario().getId() != null) {
            Usuario usuario = usuarioRepository.findById(vendedor.getUsuario().getId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            vendedor.setUsuario(usuario);
        }
        vendedor.setId(id);*/
        return repository.save(vendedor);
    }

    public List<Vendedor> buscarVendedores(Long id, String nome, String sobrenome, TipoStatus status) {
        return repository.buscarVendedores(id, nome, sobrenome, status);
    }

}