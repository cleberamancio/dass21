package br.com.byte1.dass21.domain;

import br.com.byte1.dass21.entity.Participante;

import java.util.List;
import java.util.Map;

public interface ListarParticipantePontosEscalaUserCase {
    Map<String, List<Map<String, Object>>> execute();
}