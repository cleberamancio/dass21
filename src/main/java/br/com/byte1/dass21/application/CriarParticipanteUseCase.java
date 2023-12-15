package br.com.byte1.dass21.application;

import br.com.byte1.dass21.domain.CriarParticipanteUserCase;
import br.com.byte1.dass21.domain.ParticipanteRepository;
import br.com.byte1.dass21.entity.Participante;

public class CriarParticipanteUseCase implements CriarParticipanteUserCase {
    private ParticipanteRepository repository;

    public CriarParticipanteUseCase(ParticipanteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Participante execute(Participante participante) {
        return this.repository.save(participante);
    }
}
