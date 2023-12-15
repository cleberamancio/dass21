package br.com.byte1.dass21.infrastructure;

import br.com.byte1.dass21.entity.Participante;
import br.com.byte1.dass21.entity.Resposta;

import java.util.List;

public interface ParticipanteRepositoryInterface {
    public Participante save(Participante participante);
    public Participante update(Participante participante);
    public List<Participante> listAll();
    public Participante listById(int id);
    public boolean remove(int id);
}
