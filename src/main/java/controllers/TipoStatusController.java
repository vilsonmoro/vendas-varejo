package controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.TipoStatusDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import models.TipoStatus;

@RestController
@RequestMapping("/status")
@Tag(name = "Status", description = "Operações relacionadas a status do sistema")
public class TipoStatusController {
    TipoStatusDTO tipoStatusDTO;

    @GetMapping
    public List<TipoStatusDTO> getAllStatus() {
        return Arrays.stream(TipoStatus.values())
                     .map(status -> new TipoStatusDTO(status.name(), status.getDescricao()))
                     .collect(Collectors.toList());
    }
}
