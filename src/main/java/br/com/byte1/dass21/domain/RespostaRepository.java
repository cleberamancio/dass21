package br.com.byte1.dass21.domain;

import br.com.byte1.dass21.entity.Participante;
import br.com.byte1.dass21.entity.Resposta;
import br.com.byte1.dass21.enums.Genero;
import br.com.byte1.dass21.infrastructure.RespostaRepositoryInterface;
import br.com.byte1.dass21.repository.ParticipanteRepositoryORM;
import br.com.byte1.dass21.repository.RespostaRepositoryORM;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RespostaRepository implements RespostaRepositoryInterface {

    private RespostaRepositoryORM repository;
    private ParticipanteRepositoryORM participanteRepository;

    public RespostaRepository(RespostaRepositoryORM repository, ParticipanteRepositoryORM participanteRepository)
    {
        this.repository = repository;
        this.participanteRepository = participanteRepository;
    }

    @Override
    public Resposta save(Resposta resposta, int participanteId) {
        var participante = this.participanteRepository.findById(participanteId).orElse(null);

        if( verificarSeParticipanteJaVotou(participanteId) == false ) {
            var model = new br.com.byte1.dass21.model.Resposta();
            model.setDataResposta(resposta.getDataResposta());
            model.setPontuacaoTotalAnsiedade(resposta.getPontuacaoTotalAnsiedade());
            model.setPontuacaoTotalDepressao(resposta.getPontuacaoTotalDepressao());
            model.setPontuacaoTotalEstresse(resposta.getPontuacaoTotalEstresse());
            model.setParticipante(participante);
            return fromModelToEntity(this.repository.save(model));
        }
        return null;
    }

    public Resposta update(Resposta resposta) {
        var respostaModel = this.repository.findById(resposta.getParticipante().getId()).orElse(null);
        if( respostaModel != null ) {
            respostaModel.setPontuacaoTotalAnsiedade(resposta.getPontuacaoTotalAnsiedade());
            respostaModel.setPontuacaoTotalDepressao(resposta.getPontuacaoTotalDepressao());
            respostaModel.setPontuacaoTotalEstresse(resposta.getPontuacaoTotalEstresse());
            respostaModel.setDataResposta(resposta.getDataResposta());
            respostaModel.setId(resposta.getId());
            return fromModelToEntity(this.repository.save(respostaModel));
        }
        return null;
    }

    private boolean verificarSeParticipanteJaVotou(int id)
    {
        var respostasParticipante = obterRespostasPorParticipante(id);
        if( respostasParticipante != null ) {
            return true;
        }
        return false;
    }

    public Resposta obterRespostasPorParticipante(int id) {
        var model = participanteRepository.findById(id).orElse(null);
        if( model != null ) {
            var respostasPorParticipante = this.repository.findByParticipante(model);
            if( respostasPorParticipante != null ) {
                return fromModelToEntity(respostasPorParticipante);
            }
        }
        return null;
    }

    public Resposta fromModelToEntity(br.com.byte1.dass21.model.Resposta model){
        Participante participante = new Participante();
        participante.setId(model.getParticipante().getId());
        participante.setNome(model.getParticipante().getNome());
        participante.setGenero(Genero.MASCULINO);
        if( model.getParticipante().getGenero().equals("Feminino"))
        {
            participante.setGenero(Genero.FEMININO);
        }
        participante.setIdade(model.getParticipante().getIdade());

        Resposta resposta = new Resposta();
        resposta.setId(model.getId());
        resposta.setDataResposta(model.getDataResposta());
        resposta.setParticipante(participante);
        resposta.setPontuacaoTotalAnsiedade(model.getPontuacaoTotalAnsiedade());
        resposta.setPontuacaoTotalDepressao(model.getPontuacaoTotalDepressao());
        resposta.setPontuacaoTotalEstresse(model.getPontuacaoTotalEstresse());
        return resposta;
    }
}
