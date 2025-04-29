package com.tcc2.ellemVeigaOficial.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tcc2.ellemVeigaOficial.models.Endereco;
import com.tcc2.ellemVeigaOficial.models.Usuario;
import com.tcc2.ellemVeigaOficial.repositories.EnderecoRepository;
import com.tcc2.ellemVeigaOficial.repositories.UsuarioRepository;

import lombok.AllArgsConstructor;

import java.util.List;

@Service
@AllArgsConstructor
public class EnderecoService {
    @Autowired
    private EnderecoRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Endereco> findAll(){
        return repository.findAll();
    }

    public Endereco findById(Long id){
        return repository.findById(id).get();
    }

    public Endereco addEndereco(Endereco endereco){
        if (endereco.getUsuario() != null && endereco.getUsuario().getId() != null) {
            Usuario usuario = usuarioRepository.findById(endereco.getUsuario().getId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            endereco.setUsuario(usuario);
        }
        return repository.save(endereco);
    }

    public void delete(Long id){
    repository.deleteById(id);
    }

    public Endereco update(Long id, Endereco endereco){
        if (!repository.existsById(id)) {
            throw new RuntimeException("Endereço não encontrado");}
        
        if (endereco.getUsuario() != null && endereco.getUsuario().getId() != null) {
            Usuario usuario = usuarioRepository.findById(endereco.getUsuario().getId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            endereco.setUsuario(usuario);
        }
        endereco.setId(id);
        return repository.save(endereco);
    }   
}