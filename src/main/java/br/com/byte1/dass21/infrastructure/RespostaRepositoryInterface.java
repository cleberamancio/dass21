package br.com.byte1.dass21.infrastructure;

import br.com.byte1.dass21.entity.Resposta;

public interface RespostaRepositoryInterface {
    public Resposta save(Resposta resposta, int participanteId);
}
