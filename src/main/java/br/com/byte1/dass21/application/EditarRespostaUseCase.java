package br.com.byte1.dass21.application;

import br.com.byte1.dass21.domain.CriarRespostaUserCase;
import br.com.byte1.dass21.domain.EditarRespostaUserCase;
import br.com.byte1.dass21.domain.ParticipanteRepository;
import br.com.byte1.dass21.domain.RespostaRepository;
import br.com.byte1.dass21.entity.Resposta;
import br.com.byte1.dass21.enums.Genero;

public class EditarRespostaUseCase implements EditarRespostaUserCase {
    private RespostaRepository respostaRepository;

    public EditarRespostaUseCase(RespostaRepository respostaRepository) {
        this.respostaRepository = respostaRepository;
    }

    @Override
    public Resposta execute(Resposta resposta)
    {
        var respostaDataBase = respostaRepository.obterRespostasPorParticipante(resposta.getParticipante().getId());
        respostaDataBase.setPontuacaoTotalAnsiedade(resposta.getPontuacaoTotalAnsiedade());
        respostaDataBase.setPontuacaoTotalDepressao(resposta.getPontuacaoTotalDepressao());
        respostaDataBase.setPontuacaoTotalEstresse(resposta.getPontuacaoTotalEstresse());
        respostaDataBase.setDataResposta(resposta.getDataResposta());
        return this.respostaRepository.update(respostaDataBase);
    }
}
