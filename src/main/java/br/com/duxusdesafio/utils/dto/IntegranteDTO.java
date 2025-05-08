package br.com.duxusdesafio.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntegranteDTO {
    private Long id;
    private String franquia;
    private String nome;
    private String funcao;

    public IntegranteDTO(Long id, String nome) {
    }
}