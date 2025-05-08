package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.service.IntegranteService;
import br.com.duxusdesafio.utils.dto.IntegranteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/integrantes")
public class IntegranteController {


    @Autowired
    private IntegranteService integranteService;

    @GetMapping
    public List<IntegranteDTO> listarTodos() {
        return integranteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IntegranteDTO> buscarPorId(@PathVariable Long id) {
        return integranteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<IntegranteDTO> criar(@RequestBody IntegranteDTO dto) {
        IntegranteDTO criado = integranteService.criar(dto);
        return ResponseEntity.ok(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IntegranteDTO> atualizar(@PathVariable Long id, @RequestBody IntegranteDTO dto) {
        return integranteService.atualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (integranteService.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
