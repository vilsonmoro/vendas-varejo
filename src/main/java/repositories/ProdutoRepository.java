package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    @Query("SELECT p FROM Produto p WHERE " +
           "(:codigo IS NULL OR p.id = :codigo) AND " +
           "(:nome IS NULL OR p.nome LIKE %:nome%)")
    List<Produto> buscarProdutos(@Param("codigo") Long codigo,
                                @Param("nome") String nome);
}