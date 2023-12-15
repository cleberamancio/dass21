package br.com.byte1.dass21.application;

import br.com.byte1.dass21.domain.ListarParticipantePontosEscalaByIdUserCase;
import br.com.byte1.dass21.domain.ParticipanteRepository;
import br.com.byte1.dass21.domain.RespostaRepository;
import br.com.byte1.dass21.entity.Participante;
import br.com.byte1.dass21.entity.Resposta;
import br.com.byte1.dass21.strategy.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListarParticipantePontosEscalaByIdUseCase implements ListarParticipantePontosEscalaByIdUserCase {
    private ParticipanteRepository repository;
    private RespostaRepository respostaRepository;

    public ListarParticipantePontosEscalaByIdUseCase(ParticipanteRepository repository, RespostaRepository respostaRepository) {
        this.repository = repository;
        this.respostaRepository = respostaRepository;
    }

    @Override
    public Map<String, Map<String, Object>> execute(int id) {

        Participante participante = repository.listById(id);
        Map<String, Map<String, Object>> participantesResposta = new HashMap<>();

        Map<String, Object> map = new HashMap<>();
        map.put("participante_id", participante.getId());
        map.put("idade", participante.getIdade());
        map.put("genero", participante.getGenero());

        Resposta resposta = respostaRepository.obterRespostasPorParticipante(participante.getId());
        if( resposta != null )
        {
            if (resposta.getDataResposta() != null) {
                map.put("data_resposta", new FormatacaoDataHoraStrategy(new FormatacaoBrasileiraDataHora()).format(resposta.getDataResposta()));
            }
            map.put("pontuacao_total_ansiedade", resposta.getPontuacaoTotalAnsiedade());
            map.put("escala_ansiedade", new EscalaStrategy(new EscalaAnsiedade()).escala(resposta.getPontuacaoTotalAnsiedade()));
            map.put("pontuacao_total_depressao", resposta.getPontuacaoTotalDepressao());
            map.put("escala_depressao", new EscalaStrategy(new EscalaDeprecao()).escala(resposta.getPontuacaoTotalDepressao()));
            map.put("pontuacao_total_estresse", resposta.getPontuacaoTotalEstresse());
            map.put("escala_estresse", new EscalaStrategy(new EscalaEstresse()).escala(resposta.getPontuacaoTotalEstresse()));
            participantesResposta.put("participanteResposta", map);
        }
        return participantesResposta;
    }
}
