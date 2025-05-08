package br.com.duxusdesafio.utils.dto;

import br.com.duxusdesafio.model.Integrante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeDTO {
    private Long id;

    private LocalDate data;

    List<Integrante> integrantes;

    public TimeDTO(Long id, LocalDate data) {
    }
}