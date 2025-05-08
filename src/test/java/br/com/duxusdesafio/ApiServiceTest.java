package br.com.duxusdesafio;


import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.ComposicaoTimeRepository;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.service.ApiService;
import br.com.duxusdesafio.service.TimeService;
import br.com.duxusdesafio.utils.dto.TimeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class ApiServiceTest {

    @Autowired
    private ApiService apiService;

    @Autowired
    private TimeService timeService;

    @Autowired
    private IntegranteRepository integranteRepository;

    @Autowired
    private ComposicaoTimeRepository composicaoTimeRepository;

    private Integrante faker(String nome, String funcao, String franquia) {
        return integranteRepository.save(new Integrante(null, nome, funcao, franquia));
    }

    @BeforeEach
    void setUp() {
        // Jogadores
        Integrante faker1 = faker("Faker", "Mid Laner", "T1"); // mesmo nome, franquia e função
        Integrante s1mple = faker("s1mple", "AWPer", "NAVI");
        Integrante tenz = faker("TenZ", "Duelista", "Sentinels");
        Integrante zywoo = faker("ZywOo", "AWPer", "Vitality");
        Integrante niko = faker("NiKo", "Entry Fragger", "G2");

        // Times em diferentes datas
        timeService.criar(new TimeDTO(null, LocalDate.of(2024, 5, 8), Arrays.asList(faker1, s1mple)));
        timeService.criar(new TimeDTO(null, LocalDate.of(2024, 5, 9), Arrays.asList(faker1, tenz)));
        timeService.criar(new TimeDTO(null, LocalDate.of(2024, 5, 10), Arrays.asList(faker1, zywoo)));
        timeService.criar(new TimeDTO(null, LocalDate.of(2024, 5, 11), Arrays.asList(faker1, niko)));
    }

    @Test
    void testIntegranteMaisUsado() {
        List<Time> todosOsTimes = timeService.findAll();
        Optional<Integrante> resultado = apiService.integranteMaisUsado(
                LocalDate.of(2024, 5, 7),
                LocalDate.of(2024, 5, 12),
                todosOsTimes
        );

        assertTrue(resultado.isPresent());
        assertEquals("Faker", resultado.get().getNome());  // Aparece em 3 times
    }

    @Test
    void testFuncaoMaisComum() {
        List<Time> todosOsTimes = timeService.findAll();
        Optional<String> funcao = apiService.funcaoMaisComum(
                LocalDate.of(2024, 5, 7),
                LocalDate.of(2024, 5, 12),
                todosOsTimes
        );

        assertTrue(funcao.isPresent());
        assertEquals("Mid Laner", funcao.get());  // 3 ocorrências
    }

    @Test
    void testFranquiaMaisFamosa() {
        List<Time> todosOsTimes = timeService.findAll();
        Optional<String> franquia = apiService.franquiaMaisFamosa(
                LocalDate.of(2024, 5, 7),
                LocalDate.of(2024, 5, 12),
                todosOsTimes
        );

        assertTrue(franquia.isPresent());
        assertEquals("T1", franquia.get());  // Aparece 3 vezes
    }

    @Test
    void testContagemPorFranquia() {
        List<Time> todosOsTimes = timeService.findAll();
        Map<String,Long> contagem = apiService.contagemPorFranquia(
                LocalDate.of(2024, 5, 7),
                LocalDate.of(2024, 5, 10),
                todosOsTimes
        );

        assertEquals(4, contagem.size());  // T1, NAVI, Sentinels, Vitality, G2
        assertEquals(3, contagem.get("T1"));
    }

    @Test
    void testTimeDaData() {
        List<Time> todosOsTimes = timeService.findAll();
        Optional<Time> time = apiService.timeDaData(LocalDate.of(2024, 5, 9), todosOsTimes);

        assertTrue(time.isPresent());
        assertEquals(LocalDate.of(2024, 5, 9), time.get().getData());
        assertEquals(2, time.get().getComposicao().size());
    }
}
