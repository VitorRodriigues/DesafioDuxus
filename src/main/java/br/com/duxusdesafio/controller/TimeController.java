package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.service.TimeService;
import br.com.duxusdesafio.utils.dto.TimeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/times")
public class TimeController {

    @Autowired
    private TimeService timeService;

    @GetMapping
    public List<TimeDTO> listarTodos() {
        return timeService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeDTO> buscarPorId(@PathVariable Long id) {
        return timeService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TimeDTO> criar(@RequestBody TimeDTO dto) {
        TimeDTO criado = timeService.criar(dto);
        return ResponseEntity.ok(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimeDTO> atualizar(@PathVariable Long id, @RequestBody TimeDTO dto) {
        return timeService.atualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (timeService.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}