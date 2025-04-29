package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import models.ProdutoVenda;
import models.Venda;

@Repository
public interface ProdutoVendaRepository extends JpaRepository<ProdutoVenda, Long> {
    List<ProdutoVenda> findByVenda(Venda venda);

    @Query("SELECT pv FROM ProdutoVenda pv WHERE " +
           "(:codigoVenda IS NULL OR pv.venda.id = :codigoVenda) AND " +
           "(:nomeProduto IS NULL OR pv.produto.nome LIKE %:nomeProduto%)")
    List<ProdutoVenda> buscarProdutoVendas(@Param("codigoVenda") Long codigoVenda,
                                          @Param("nomeProduto") String nomeProduto);
}