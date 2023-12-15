package br.com.byte1.dass21.domain;

import br.com.byte1.dass21.entity.Participante;
import br.com.byte1.dass21.entity.Resposta;
import br.com.byte1.dass21.enums.Genero;
import br.com.byte1.dass21.infrastructure.ParticipanteRepositoryInterface;
import br.com.byte1.dass21.infrastructure.RespostaRepositoryInterface;
import br.com.byte1.dass21.repository.ParticipanteRepositoryORM;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
@Repository
public class ParticipanteRepository implements ParticipanteRepositoryInterface {

    private ParticipanteRepositoryORM repository;

    public ParticipanteRepository(ParticipanteRepositoryORM repository)
    {
        this.repository = repository;
    }

    @Override
    public Participante save(Participante participante) {
        var participanteModel = new br.com.byte1.dass21.model.Participante();
        participanteModel.setNome(participante.getNome());
        participanteModel.setIdade(participante.getIdade());

        participanteModel.setGenero(Genero.MASCULINO);
        if( participante.getGenero().equals("Feminino"))
        {
            participanteModel.setGenero(Genero.FEMININO);
        }
        return fromModelToEntity(this.repository.save(participanteModel));
    }

    public Participante update(Participante participante) {
        var participanteModel = repository.findById(participante.getId()).orElse(null);
        if( participanteModel != null )
        {
            participanteModel.setNome(participante.getNome());
            participanteModel.setIdade(participante.getIdade());
            participanteModel.setGenero(Genero.MASCULINO);
            if (participante.getGenero().equals("Feminino")) {
                participanteModel.setGenero(Genero.FEMININO);
            }


            return fromModelToEntity(this.repository.save(participanteModel));
        }
        return null;
    }

    @Override
    public List<Participante> listAll() {
        List<Participante> list = this.repository.findAll()
                .stream().map(model -> fromModelToEntity(model))
                .collect(Collectors.toList());
        return list;
    }


    @Override
    public Participante listById(int id) {
        br.com.byte1.dass21.model.Participante participanteReturned = this.repository.findById(id).orElse(null);
        if( participanteReturned != null ) {
            return fromModelToEntity(participanteReturned);
        }
        return null;
    }

    @Override
    public boolean remove(int id) {
        br.com.byte1.dass21.model.Participante model = this.repository.findById(id).orElse(null);
        if( model != null ){
            this.repository.delete(model);
            return true;
        }
        return false;
    }

    public Participante fromModelToEntity(br.com.byte1.dass21.model.Participante model){
        Participante participante = new Participante();
        participante.setId(model.getId());
        participante.setNome(model.getNome());
        participante.setIdade(model.getIdade());
        participante.setGenero(Genero.MASCULINO);
        if (model.getGenero().equals("Feminino")) {
            participante.setGenero(Genero.FEMININO);
        }
        return participante;
    }

    public br.com.byte1.dass21.model.Participante fromEntityToModel(Participante entity){
        var model = new br.com.byte1.dass21.model.Participante();
        model.setId(entity.getId());
        model.setNome(entity.getNome());
        model.setIdade(entity.getIdade());
        model.setGenero(Genero.MASCULINO);
        if (entity.getGenero().equals("Feminino")) {
            model.setGenero(Genero.FEMININO);
        }
        return model;
    }
}
