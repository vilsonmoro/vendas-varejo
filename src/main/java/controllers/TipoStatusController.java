package controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.TipoStatusDTO;
import models.TipoStatus;

@RestController
@RequestMapping("/status")
public class TipoStatusController {
    TipoStatusDTO tipoStatusDTO;

    @GetMapping
    public List<TipoStatusDTO> getAllStatus() {
        return Arrays.stream(TipoStatus.values())
                     .map(status -> new TipoStatusDTO(status.name(), status.getDescricao()))
                     .collect(Collectors.toList());
    }
}
