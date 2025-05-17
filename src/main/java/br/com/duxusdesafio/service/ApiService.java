package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ApiService {

    // Método auxiliar para filtrar times dentro do intervalo de datas
    private List<Time> filtrarTimesPorPeriodo(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        return todosOsTimes.stream()
                .filter(time -> (dataInicial == null || !time.getData().isBefore(dataInicial)) &&
                        (dataFinal == null || !time.getData().isAfter(dataFinal)))
                .collect(Collectors.toList());
    }

    /**
     * Retorna o time da data fornecida, incluindo os integrantes desse time
     */
    public Time timeDaData(LocalDate data, List<Time> todosOsTimes) {
        return todosOsTimes.stream()
                .filter(time -> time.getData().equals(data))
                .findFirst().orElse(null);
    }

    /**
     * Retorna o integrante mais usado dentro do período
     */
    public Integrante integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        List<Time> timesFiltrados = filtrarTimesPorPeriodo(dataInicial, dataFinal, todosOsTimes);

        return timesFiltrados.stream()
                .flatMap(time -> time.getComposicao().stream())
                .map(ComposicaoTime::getIntegrante)
                .collect(Collectors.groupingBy(integrante -> integrante, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(null);
    }

    /**
     * Retorna uma lista com os integrantes do time mais comum dentro do período
     */
    public List<String> integrantesDoTimeMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        List<Time> timesFiltrados = filtrarTimesPorPeriodo(dataInicial, dataFinal, todosOsTimes);

        Long mostCommonTimeId = timesFiltrados.stream()
                .collect(Collectors.groupingBy(Time::getId, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        return timesFiltrados.stream()
                .filter(time -> time.getId().equals(mostCommonTimeId))
                .flatMap(time -> time.getComposicao().stream())
                .map(composicao -> composicao.getIntegrante().getNome())
                .collect(Collectors.toList());
    }

    /**
     * Retorna a função mais comum dentro do período
     */
    public String funcaoMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        List<Time> timesFiltrados = filtrarTimesPorPeriodo(dataInicial, dataFinal, todosOsTimes);

        Optional<String> opt = timesFiltrados.stream()
                .flatMap(time -> time.getComposicao().stream())
                .map(composicao -> composicao.getIntegrante().getFuncao())
                .collect(Collectors.groupingBy(funcao -> funcao, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);

        return opt.orElse(null);
    }

    /**
     * Retorna a franquia mais comum dentro do período
     */
    public String franquiaMaisFamosa(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        List<Time> timesFiltrados = filtrarTimesPorPeriodo(dataInicial, dataFinal, todosOsTimes);

        return timesFiltrados.stream()
                .flatMap(time -> time.getComposicao().stream())
                .map(ComposicaoTime::getIntegrante)
                .collect(Collectors.groupingBy(Integrante::getFranquia, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(null);
    }

    /**
     * Retorna a contagem de franquias dentro do período
     */
    public Map<String, Long> contagemPorFranquia(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        List<Time> timesFiltrados = filtrarTimesPorPeriodo(dataInicial, dataFinal, todosOsTimes);

        return timesFiltrados.stream()
                .flatMap(time -> time.getComposicao().stream())
                .map(composicao -> composicao.getIntegrante().getFranquia())
                .collect(Collectors.groupingBy(franquia -> franquia, Collectors.counting()));
    }

    /**
     * Retorna a contagem de funções dentro do período
     */
    public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        List<Time> timesFiltrados = filtrarTimesPorPeriodo(dataInicial, dataFinal, todosOsTimes);

        return timesFiltrados.stream()
                .flatMap(time -> time.getComposicao().stream())
                .map(composicao -> composicao.getIntegrante().getFuncao())
                .collect(Collectors.groupingBy(funcao -> funcao, Collectors.counting()));
    }
}