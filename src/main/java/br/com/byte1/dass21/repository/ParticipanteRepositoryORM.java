package br.com.byte1.dass21.repository;

import br.com.byte1.dass21.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipanteRepositoryORM extends JpaRepository<Participante, Integer> {
}