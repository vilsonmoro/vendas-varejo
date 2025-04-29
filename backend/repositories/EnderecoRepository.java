package com.tcc2.ellemVeigaOficial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.tcc2.ellemVeigaOficial.models.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findById(long id);
}
