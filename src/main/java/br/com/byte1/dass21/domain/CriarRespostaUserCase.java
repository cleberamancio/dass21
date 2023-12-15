package br.com.byte1.dass21.domain;

import br.com.byte1.dass21.entity.Resposta;

public interface CriarRespostaUserCase {
    Resposta execute(Resposta resposta, int participanteId);
}