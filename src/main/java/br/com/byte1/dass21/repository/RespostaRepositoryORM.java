package br.com.byte1.dass21.repository;

import br.com.byte1.dass21.model.Participante;
import br.com.byte1.dass21.model.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespostaRepositoryORM extends JpaRepository<Resposta, Integer> {
    Resposta findByParticipante(Participante participante);

}
