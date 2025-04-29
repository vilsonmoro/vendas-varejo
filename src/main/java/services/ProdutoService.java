package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.Produto;
import models.Usuario;
import repositories.ProdutoRepository;
import repositories.UsuarioRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Produto addProduto(Produto produto){
        if (produto.getUsuario() != null && produto.getUsuario().getId() != null) {
            Usuario usuario = usuarioRepository.findById(produto.getUsuario().getId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            produto.setUsuario(usuario);
        }
        return repository.save(produto);
    }

    public Produto findById(Long id){
        return repository.findById(id).get();
    }

    public List<Produto> findAll(){
        return repository.findAll();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Produto update(Long id, Produto produto){
        if (!repository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado");
        }
        produto.setId(id);

        if (produto.getUsuario() != null && produto.getUsuario().getId() != null) {
            Usuario usuario = usuarioRepository.findById(produto.getUsuario().getId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            produto.setUsuario(usuario);
        }

        return repository.save(produto);
    }

    public List<Produto> buscarProdutos(Long codigo, String nome) {
        return repository.buscarProdutos(codigo, nome);
    }
}
