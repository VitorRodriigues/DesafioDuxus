package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.service.ApiService;
import br.com.duxusdesafio.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @Autowired
    private TimeService timeService;

    private ResponseEntity<Map<String, String>> notFoundMessage(String message) {
        Map<String, String> body = new HashMap<>();
        body.put("message", message);
        return ResponseEntity.status(404).body(body);
    }

    @GetMapping("/time-da-data")
    public ResponseEntity<?> getTimeDaData(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        if (data == null) {
            throw new IllegalArgumentException("A data é obrigatória.");
        }

        List<Time> todosOsTimes = timeService.findAll();
        Time time = apiService.timeDaData(data, todosOsTimes);

        if (time == null) {
            return notFoundMessage("Nenhum time encontrado para a data informada.");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("id", time.getId());
        response.put("date", time.getData());
        response.put("integrantes", time.getComposicoes().stream()
                .map(composicao -> composicao.getIntegrante().getNome())
                .collect(Collectors.toList()));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/integrante-mais-usado")
    public ResponseEntity<?> getIntegranteMaisUsado(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {

        if (dataInicial != null && dataFinal != null && dataFinal.isBefore(dataInicial)) {
            throw new IllegalArgumentException("A data final não pode ser anterior à data inicial.");
        }

        List<Time> todosOsTimes = timeService.findAll();
        Integrante integrante = apiService.integranteMaisUsado(dataInicial, dataFinal, todosOsTimes);

        if (integrante == null) {
            return notFoundMessage("Nenhum integrante encontrado no período informado.");
        }

        return ResponseEntity.ok(integrante);
    }

    @GetMapping("/integrantes-do-time-mais-comum")
    public ResponseEntity<?> getIntegrantesDoTimeMaisComum(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {

        if (dataInicial != null && dataFinal != null && dataFinal.isBefore(dataInicial)) {
            throw new IllegalArgumentException("A data final não pode ser anterior à data inicial.");
        }

        List<Time> todosOsTimes = timeService.findAll();
        List<String> integrantes = apiService.integrantesDoTimeMaisComum(dataInicial, dataFinal, todosOsTimes);

        if (integrantes == null) {
            return notFoundMessage("Nenhum integrante encontrado no período informado.");
        }

        return ResponseEntity.ok(integrantes);
    }

    @GetMapping(value = "/funcao-mais-comum", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> getFuncaoMaisComum(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {

        List<Time> todosOsTimes = timeService.findAll();
        String funcao = apiService.funcaoMaisComum(dataInicial, dataFinal, todosOsTimes);

        if (funcao == null) {
            return notFoundMessage("Nenhuma função encontrada no período informado.");
        }

        return ResponseEntity.ok(funcao);
    }

    @GetMapping(value = "/franquia-mais-famosa", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> getFranquiaMaisFamosa(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {

        List<Time> todosOsTimes = timeService.findAll();
        String franquia = apiService.franquiaMaisFamosa(dataInicial, dataFinal, todosOsTimes);

        if (franquia == null) {
            return notFoundMessage("\"Nenhuma franquia encontrada no período informado.");
        }

        return ResponseEntity.ok(franquia);
    }

    @GetMapping("/contagem-por-franquia")
    public ResponseEntity<?> getContagemPorFranquia(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {

        List<Time> todosOsTimes = timeService.findAll();
        Map<String, Long> contagem = apiService.contagemPorFranquia(dataInicial, dataFinal, todosOsTimes);

        if (contagem == null || contagem.isEmpty()) {
            return notFoundMessage("Nenhuma contagem encontrada no período informado.");
        }

        return ResponseEntity.ok(contagem);
    }

    @GetMapping("/contagem-por-funcao")
    public ResponseEntity<?> getContagemPorFuncao(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {

        List<Time> todosOsTimes = timeService.findAll();
        Map<String, Long> contagem = apiService.contagemPorFuncao(dataInicial, dataFinal, todosOsTimes);

        if (contagem == null || contagem.isEmpty()) {
            return notFoundMessage("Nenhuma contagem encontrada no período informado.");
        }

        return ResponseEntity.ok(contagem);
    }
}