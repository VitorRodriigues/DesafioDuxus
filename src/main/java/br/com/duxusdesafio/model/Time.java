package br.com.duxusdesafio.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "time")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;

    @OneToMany(mappedBy = "time", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<ComposicaoTime> composicoes = new ArrayList<>();

    public List<ComposicaoTime> getComposicao() {
        return composicoes;
    }

    public void setComposicao(List<ComposicaoTime> composicoes) {
        this.composicoes = composicoes;
    }
}