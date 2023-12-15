package br.com.byte1.dass21.domain;

import br.com.byte1.dass21.entity.Participante;
import br.com.byte1.dass21.entity.Resposta;

public interface SalvarQuestionarioUserCase {
    int execute(Resposta participante);
}