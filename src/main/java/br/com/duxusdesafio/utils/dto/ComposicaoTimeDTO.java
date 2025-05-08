package br.com.duxusdesafio.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComposicaoTimeDTO {
    private Long idTime;
    private Long idIntegrante;
}
