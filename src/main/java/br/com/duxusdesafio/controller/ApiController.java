package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.service.ApiService;
import br.com.duxusdesafio.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @Autowired
    private TimeService timeService;

    @GetMapping("/time-da-data")
    public Optional<Time> getTimeDaData(@RequestParam
                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        List<Time> todosOsTimes = timeService.findAll();
        return apiService.timeDaData(data, todosOsTimes);
    }

    @GetMapping("/integrante-mais-usado")
    public Optional<Integrante> getIntegranteMaisUsado(@RequestParam(required = false)
                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
                                                       @RequestParam(required = false)
                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
        List<Time> todosOsTimes = timeService.findAll();
        return apiService.integranteMaisUsado(dataInicial, dataFinal, todosOsTimes);
    }

    @GetMapping("/integrantes-do-time-mais-comum")
    public List<String> getIntegrantesDoTimeMaisComum(@RequestParam(required = false)
                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
                                                      @RequestParam(required = false)
                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
        List<Time> todosOsTimes = timeService.findAll();
        return apiService.integrantesDoTimeMaisComum(dataInicial, dataFinal, todosOsTimes);
    }

    @GetMapping("/funcao-mais-comum")
    public Optional<String> getFuncaoMaisComum(@RequestParam(required = false)
                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
                                               @RequestParam(required = false)
                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
        List<Time> todosOsTimes = timeService.findAll();
        return apiService.funcaoMaisComum(dataInicial, dataFinal, todosOsTimes);
    }

    @GetMapping("/franquia-mais-famosa")
    public Optional<String> getFranquiaMaisFamosa(@RequestParam(required = false)
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
                                                  @RequestParam(required = false)
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
        List<Time> todosOsTimes = timeService.findAll();
        return apiService.franquiaMaisFamosa(dataInicial, dataFinal, todosOsTimes);
    }

    @GetMapping("/contagem-por-franquia")
    public Map<String, Long> getContagemPorFranquia(@RequestParam(required = false)
                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
                                                    @RequestParam(required = false)
                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate dataFinal) {
        List<Time> todosOsTimes = timeService.findAll();
        return apiService.contagemPorFranquia(dataInicial, dataFinal, todosOsTimes);
    }

    @GetMapping("/contagem-por-funcao")
    public Map<String, Long> getContagemPorFuncao(@RequestParam(required = false)
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
                                                  @RequestParam(required = false)
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
        List<Time> todosOsTimes = timeService.findAll();
        return apiService.contagemPorFuncao(dataInicial, dataFinal, todosOsTimes);
    }
}