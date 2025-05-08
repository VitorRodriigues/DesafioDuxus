package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.ComposicaoTimeRepository;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;
import br.com.duxusdesafio.utils.dto.ComposicaoTimeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComposicaoTimeService {

    @Autowired
    private ComposicaoTimeRepository composicaoTimeRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private IntegranteRepository integranteRepository;

    public ComposicaoTime create(ComposicaoTimeDTO dto) {
        Time time = timeRepository.findById(dto.getIdTime())
                .orElseThrow(() -> new RuntimeException("Time não encontrado"));

        Integrante integrante = integranteRepository.findById(dto.getIdIntegrante())
                .orElseThrow(() -> new RuntimeException("Integrante não encontrado"));

        ComposicaoTime composicao = new ComposicaoTime();
        composicao.setTime(time);
        composicao.setIntegrante(integrante);

        composicaoTimeRepository.save(composicao);

        return composicao;
    }

    public List<ComposicaoTimeDTO> getAll() {
        return composicaoTimeRepository.findAll().stream()
                .map(c -> {
                    ComposicaoTimeDTO dto = new ComposicaoTimeDTO();
                    dto.setIdTime(c.getTime().getId());
                    dto.setIdIntegrante(c.getIntegrante().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public List<ComposicaoTimeDTO> getByIdTime(Long idTime) {
        return composicaoTimeRepository.findByTimeId(idTime).stream()
                .map(c -> {
                    ComposicaoTimeDTO dto = new ComposicaoTimeDTO();
                    dto.setIdTime(c.getTime().getId());
                    dto.setIdIntegrante(c.getIntegrante().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}