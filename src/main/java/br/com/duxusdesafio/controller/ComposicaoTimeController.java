package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.service.ComposicaoTimeService;
import br.com.duxusdesafio.utils.dto.ComposicaoTimeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/composicao")
@RequiredArgsConstructor
public class ComposicaoTimeController {

    private final ComposicaoTimeService composicaoTimeService;

    @PostMapping
    public ResponseEntity<ComposicaoTime> create(@RequestBody ComposicaoTimeDTO dto) {
        ComposicaoTime saved = composicaoTimeService.create(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<ComposicaoTimeDTO>> getAll() {
        return ResponseEntity.ok(composicaoTimeService.getAll());
    }

    @GetMapping("/por-time/{idTime}")
    public ResponseEntity<List<ComposicaoTimeDTO>> getByIdTime(@PathVariable Long idTime) {
        return ResponseEntity.ok(composicaoTimeService.getByIdTime(idTime));
    }
}
