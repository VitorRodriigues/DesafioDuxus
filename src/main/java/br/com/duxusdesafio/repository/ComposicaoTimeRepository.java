package br.com.duxusdesafio.repository;

import br.com.duxusdesafio.model.ComposicaoTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComposicaoTimeRepository extends JpaRepository<ComposicaoTime, Long> {
    List<ComposicaoTime> findByTimeId(Long idTime);
}
