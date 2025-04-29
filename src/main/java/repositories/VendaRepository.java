package repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import models.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long>{
       List<Venda> findByDataBetween(Date dataInicio, Date dataFim);
       
    @Query("SELECT v FROM Venda v JOIN v.cliente c WHERE " +
           "(:idVenda IS NULL OR v.id = :idVenda) AND " +
           "(:nomeCliente IS NULL OR c.nome LIKE %:nomeCliente%) AND " +
           "(:idPedido IS NULL OR v.pedido.id = :idPedido) AND " +
           "(:dataInicial IS NULL OR v.data >= :dataInicial) AND " +
           "(:dataFinal IS NULL OR v.data <= :dataFinal)")
    List<Venda> buscarVendas(@Param("idVenda") Long idVenda,
                            @Param("nomeCliente") String nomeCliente,
                            @Param("idPedido") Long idPedido,
                            @Param("dataInicial") Date dataInicial,
                            @Param("dataFinal") Date dataFinal);
}