package br.com.byte1.dass21.application;

import br.com.byte1.dass21.domain.ListarParticipanteByIdUserCase;
import br.com.byte1.dass21.domain.ParticipanteRepository;
import br.com.byte1.dass21.entity.Participante;

public class ListarParticipanteByIdUseCase implements ListarParticipanteByIdUserCase {
    private ParticipanteRepository repository;

    public ListarParticipanteByIdUseCase(ParticipanteRepository repository) {
        this.repository = repository;
    }


    @Override
    public Participante execute(int id) {
        return this.repository.listById(id);
    }
}
