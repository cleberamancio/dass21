package br.com.byte1.dass21.domain;

import br.com.byte1.dass21.entity.Participante;

import java.time.LocalDateTime;

public interface EditarParticipanteUserCase {
    Participante execute(Participante participante, int ansiedade, int depressao, int estresse,  LocalDateTime dataHora);
}