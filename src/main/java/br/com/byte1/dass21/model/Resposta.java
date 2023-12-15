package br.com.byte1.dass21.model;

import br.com.byte1.dass21.strategy.ValidacaoPontuacao;
import br.com.byte1.dass21.strategy.ValidacaoPontuacaoResposta;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Respostas")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "participante_id", nullable = false)
    private Participante participante;

    @Column(name = "data_resposta", nullable = false)
    private LocalDateTime dataResposta;

    @Column(name = "pontuacao_total_depressao", nullable = false)
    private int pontuacaoTotalDepressao;

    @Column(name = "pontuacao_total_ansiedade", nullable = false)
    private int pontuacaoTotalAnsiedade;

    @Column(name = "pontuacao_total_estresse", nullable = false)
    private int  pontuacaoTotalEstresse; //INT NOT NULL,

    public Resposta(){}
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
