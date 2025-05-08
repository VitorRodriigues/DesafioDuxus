package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.TimeRepository;
import br.com.duxusdesafio.utils.dto.ComposicaoTimeDTO;
import br.com.duxusdesafio.utils.dto.TimeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TimeService {

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private ComposicaoTimeService composicaoTimeService;

    public List<TimeDTO> listarTodos() {
        return timeRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Time> findAll() {
        return timeRepository.findAll();
    }

    public Optional<TimeDTO> buscarPorId(Long id) {
        return timeRepository.findById(id).map(this::toDTO);
    }

    public TimeDTO criar(TimeDTO dto) {
        Time time = toEntity(dto);
        timeRepository.save(time);

        time.setComposicoes(preencheComposicoes(time, dto));

        return toDTO(timeRepository.save(time));
    }

    public Optional<TimeDTO> atualizar(Long id, TimeDTO dto) {
        return timeRepository.findById(id).map(time -> {
            time.setData(dto.getData());
            return toDTO(timeRepository.save(time));
        });
    }

    public boolean deletar(Long id) {
        if (!timeRepository.existsById(id)) return false;
        timeRepository.deleteById(id);
        return true;
    }

    private TimeDTO toDTO(Time time) {
        return new TimeDTO(time.getId(), time.getData());
    }

    private Time toEntity(TimeDTO dto) {
        Time t = new Time();
        t.setId(dto.getId());
        t.setData(dto.getData());
        return t;
    }

    private List<ComposicaoTime> preencheComposicoes(Time time, TimeDTO dto) {
        List<ComposicaoTime> composicoes = new ArrayList<>();

        for (Integrante integrante : dto.getIntegrantes()) {
            ComposicaoTimeDTO composicaoDTO = new ComposicaoTimeDTO(time.getId(), integrante.getId());
            ComposicaoTime composicaoTime = composicaoTimeService.create(composicaoDTO);
            composicoes.add(composicaoTime);
        }

        return composicoes;
    }
}