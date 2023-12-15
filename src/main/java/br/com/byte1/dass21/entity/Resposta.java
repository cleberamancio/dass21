package br.com.byte1.dass21.entity;

import br.com.byte1.dass21.strategy.ValidacaoIdade;
import br.com.byte1.dass21.strategy.ValidacaoIdadeParticipante;
import br.com.byte1.dass21.strategy.ValidacaoPontuacao;
import br.com.byte1.dass21.strategy.ValidacaoPontuacaoResposta;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Resposta {
    private long id;
    private Participante participante;
    private LocalDateTime dataResposta; //TIMESTAMP NOT NULL,
    @JsonProperty("pontuacao_total_depressao")
    private int pontuacaoTotalDepressao; //INT NOT NULL,
    @JsonProperty("pontuacao_total_ansiedade")
    private int pontuacaoTotalAnsiedade; //INT NOT NULL,
    @JsonProperty("pontuacao_total_estresse")
    private int  pontuacaoTotalEstresse; //INT NOT NULL,

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public LocalDateTime getDataResposta() {
        return dataResposta;
    }

    public void setDataResposta(LocalDateTime dataResposta) {
        this.dataResposta = dataResposta;
    }

    public int getPontuacaoTotalDepressao() {
        return pontuacaoTotalDepressao;
    }

    public void setPontuacaoTotalDepressao(int pontuacaoTotalDepressao) {
        new ValidacaoPontuacao( new ValidacaoPontuacaoResposta()).validate(pontuacaoTotalDepressao);
        this.pontuacaoTotalDepressao = pontuacaoTotalDepressao;
    }

    public int getPontuacaoTotalAnsiedade() {
        return pontuacaoTotalAnsiedade;
    }

    public void setPontuacaoTotalAnsiedade(int pontuacaoTotalAnsiedade) {
        new ValidacaoPontuacao( new ValidacaoPontuacaoResposta()).validate(pontuacaoTotalAnsiedade);
        this.pontuacaoTotalAnsiedade = pontuacaoTotalAnsiedade;
    }

    public int getPontuacaoTotalEstresse() {
        return pontuacaoTotalEstresse;
    }

    public void setPontuacaoTotalEstresse(int pontuacaoTotalEstresse) {
        new ValidacaoPontuacao( new ValidacaoPontuacaoResposta()).validate(pontuacaoTotalEstresse);
        this.pontuacaoTotalEstresse = pontuacaoTotalEstresse;
    }
}
