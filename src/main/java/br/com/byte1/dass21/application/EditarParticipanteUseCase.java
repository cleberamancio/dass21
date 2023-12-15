package br.com.byte1.dass21.application;

import br.com.byte1.dass21.domain.CriarParticipanteUserCase;
import br.com.byte1.dass21.domain.EditarParticipanteUserCase;
import br.com.byte1.dass21.domain.ParticipanteRepository;
import br.com.byte1.dass21.domain.RespostaRepository;
import br.com.byte1.dass21.entity.Participante;
import br.com.byte1.dass21.entity.Resposta;
import br.com.byte1.dass21.enums.Genero;

import java.time.LocalDateTime;

public class EditarParticipanteUseCase implements EditarParticipanteUserCase {
    private ParticipanteRepository participanteRepository;
    private RespostaRepository respostaRepository;

    public EditarParticipanteUseCase(ParticipanteRepository participanteRepository, RespostaRepository respostaRepository) {
        this.participanteRepository = participanteRepository;
        this.respostaRepository = respostaRepository;
    }

    @Override
    public Participante execute(Participante participante, int ansiedade, int depressao, int estresse, LocalDateTime dataHora)
    {
        var participanteDataBase = new ListarParticipanteByIdUseCase(participanteRepository).execute(participante.getId());
        if( participanteDataBase != null )
        {
            Resposta resposta = respostaRepository.obterRespostasPorParticipante(participante.getId());
            resposta.setId(resposta.getId());
            resposta.setParticipante(resposta.getParticipante());
            resposta.setPontuacaoTotalAnsiedade(ansiedade);
            resposta.setPontuacaoTotalDepressao(depressao);
            resposta.setPontuacaoTotalEstresse(estresse);
            resposta.setDataResposta(dataHora);
            var editarRespostaUseCase = new EditarRespostaUseCase(this.respostaRepository);
            editarRespostaUseCase.execute(resposta);

            participanteDataBase.setNome(participante.getNome());
            participanteDataBase.setIdade(participante.getIdade());
            participanteDataBase.setGenero(Genero.MASCULINO);
            if (participante.getGenero().equals("Feminino")) {
                participanteDataBase.setGenero(Genero.FEMININO);
            }
            return this.participanteRepository.update(participanteDataBase);
        }
        return null;
    }
}
