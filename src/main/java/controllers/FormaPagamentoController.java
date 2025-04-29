package controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.FormaPagamentoDTO;
import models.FormaPagamento;

@RestController
@RequestMapping("/formapagamento")
public class FormaPagamentoController {
    FormaPagamentoDTO formaPagamentoDTO;

    @GetMapping
    public List<FormaPagamentoDTO> getAllFormasPagamento() {
        return Arrays.stream(FormaPagamento.values())
                     .map(forma -> new FormaPagamentoDTO(forma.name(), forma.getDescricao()))
                     .collect(Collectors.toList());
    }
}