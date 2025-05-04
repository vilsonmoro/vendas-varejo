package controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.TipoEntregaDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import models.TipoEntrega;

@RestController
@RequestMapping("/tipoentrega")
@Tag(name = "Tipo de entrega", description = "Operações relacionadas ao tipo de entrega")
public class TipoEntregaController {
    TipoEntregaDTO tipoEntregaDTO;

    @GetMapping
    public List<TipoEntregaDTO> getAllTiposEntrega() {
        return Arrays.stream(TipoEntrega.values())
                     .map(tipo -> new TipoEntregaDTO(tipo.name(), tipo.getDescricao()))
                     .collect(Collectors.toList());
    }
}
