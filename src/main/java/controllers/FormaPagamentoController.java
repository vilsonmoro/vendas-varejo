package controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.FormaPagamentoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import models.FormaPagamento;

@RestController
@RequestMapping("/formapagamento")
@Tag(name = "Forma de pagamento", description = "Operações relacionadas a forma de pagamento")
public class FormaPagamentoController {
    FormaPagamentoDTO formaPagamentoDTO;

    @GetMapping
    @Operation(summary = "Retorna todos as formas de pagamentos cadastradas")
    public List<FormaPagamentoDTO> getAllFormasPagamento() {
        return Arrays.stream(FormaPagamento.values())
                     .map(forma -> new FormaPagamentoDTO(forma.name(), forma.getDescricao()))
                     .collect(Collectors.toList());
    }
}