package br.com.duxusdesafio.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "integrante")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Integrante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String funcao;

    private String franquia;
}