package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import models.Produto;
import models.ProdutoVenda;
import models.Usuario;
import models.Venda;
import repositories.ProdutoRepository;
import repositories.ProdutoVendaRepository;
import repositories.UsuarioRepository;
import repositories.VendaRepository;

@Service
public class ProdutoVendaService {
    @Autowired
    private ProdutoVendaRepository repository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public ProdutoVenda addProdutoVenda(ProdutoVenda produtoVenda){
        if (produtoVenda.getUsuario() != null && produtoVenda.getUsuario().getId() != null) {
            Usuario usuario = usuarioRepository.findById(produtoVenda.getUsuario().getId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            produtoVenda.setUsuario(usuario);
        }

        if (produtoVenda.getProduto() != null && produtoVenda.getProduto().getId() != null) {
            Produto produto = produtoRepository.findById(produtoVenda.getProduto().getId()).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            produtoVenda.setProduto(produto);
        }

        if (produtoVenda.getVenda() != null && produtoVenda.getVenda().getId() != null) {
            Venda venda = vendaRepository.findById(produtoVenda.getVenda().getId()).orElseThrow(() -> new RuntimeException("Venda não encontrada"));
            produtoVenda.setVenda(venda);
        }

        return repository.save(produtoVenda);
    }

    public ProdutoVenda findById(Long id){
        return repository.findById(id).get();
    }

    public List<ProdutoVenda> findAll(){
        return repository.findAll();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    @Transactional
    public ProdutoVenda update(Long id, ProdutoVenda produtoVenda){
        if (!repository.existsById(id)) {
            throw new RuntimeException("Relação produto X venda não encontrada");}
        produtoVenda.setId(id);

        if (produtoVenda.getUsuario() != null && produtoVenda.getUsuario().getId() != null) {
            Usuario usuario = usuarioRepository.findById(produtoVenda.getUsuario().getId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            produtoVenda.setUsuario(usuario);
        }

        if (produtoVenda.getProduto() != null && produtoVenda.getProduto().getId() != null) {
            Produto produto = produtoRepository.findById(produtoVenda.getProduto().getId()).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            produtoVenda.setProduto(produto);
        }
        
        if (produtoVenda.getVenda() != null && produtoVenda.getVenda().getId() != null) {
            Venda venda = vendaRepository.findById(produtoVenda.getVenda().getId()).orElseThrow(() -> new RuntimeException("Venda não encontrada"));
            produtoVenda.setVenda(venda);
        }
        
        return repository.save(produtoVenda);
    }

    public List<ProdutoVenda> buscarProdutoVendas(Long idVenda, String nomeProduto) {
        return repository.buscarProdutoVendas(idVenda, nomeProduto);
    }
}