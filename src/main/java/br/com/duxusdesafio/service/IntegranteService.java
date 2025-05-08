package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.utils.dto.IntegranteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IntegranteService {

    @Autowired
    private IntegranteRepository integranteRepository;

    public List<IntegranteDTO> listarTodos() {
        return integranteRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<IntegranteDTO> buscarPorId(Long id) {
        return integranteRepository.findById(id).map(this::toDTO);
    }

    public IntegranteDTO criar(IntegranteDTO dto) {
        Integrante integrante = toEntity(dto);
        return toDTO(integranteRepository.save(integrante));
    }

    public Optional<IntegranteDTO> atualizar(Long id, IntegranteDTO dto) {
        return integranteRepository.findById(id).map(integrante -> {
            integrante.setNome(dto.getNome());
            integrante.setFranquia(dto.getFranquia());
            integrante.setFuncao(dto.getFuncao());
            return toDTO(integranteRepository.save(integrante));
        });
    }

    public boolean deletar(Long id) {
        if (!integranteRepository.existsById(id)) return false;
        integranteRepository.deleteById(id);
        return true;
    }

    private IntegranteDTO toDTO(Integrante integrante) {
        return new IntegranteDTO(integrante.getId(), integrante.getFranquia(), integrante.getNome(), integrante.getFuncao());
    }

    private Integrante toEntity(IntegranteDTO dto) {
        Integrante i = new Integrante();
        i.setId(dto.getId());
        i.setFranquia(dto.getFranquia());
        i.setNome(dto.getNome());
        i.setFuncao(dto.getFuncao());
        return i;
    }
}
