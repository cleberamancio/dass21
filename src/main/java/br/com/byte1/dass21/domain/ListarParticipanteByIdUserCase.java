package br.com.byte1.dass21.domain;

import br.com.byte1.dass21.entity.Participante;

import java.util.List;

public interface ListarParticipanteByIdUserCase {
    Participante execute(int id);
}