package br.com.byte1.dass21.application;

import br.com.byte1.dass21.domain.ParticipanteRepository;
import br.com.byte1.dass21.domain.RespostaRepository;
import br.com.byte1.dass21.domain.CriarRespostaUserCase;
import br.com.byte1.dass21.entity.Participante;
import br.com.byte1.dass21.entity.Resposta;

public class SalvarRespostaUseCase implements CriarRespostaUserCase {
    private RespostaRepository respostaRepository;

    public SalvarRespostaUseCase(RespostaRepository respostaRepository) {
        this.respostaRepository = respostaRepository;
    }

    @Override
    public Resposta execute(Resposta resposta, int participanteId) {
        return this.respostaRepository.save(resposta, participanteId);
    }
}
