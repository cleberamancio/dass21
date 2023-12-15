package br.com.byte1.dass21.domain;

import java.util.List;
import java.util.Map;

public interface ListarParticipantePontosEscalaByIdUserCase {
    Map<String, Map<String, Object>> execute(int id);
}