package br.com.byte1.dass21.adapter.controller;

import br.com.byte1.dass21.adapter.dto.ParticipanteDTO;
import br.com.byte1.dass21.adapter.dto.ParticipanteEdicaoDTO;
import br.com.byte1.dass21.application.*;
import br.com.byte1.dass21.domain.ParticipanteRepository;
import br.com.byte1.dass21.domain.RespostaRepository;
import br.com.byte1.dass21.entity.Participante;
import br.com.byte1.dass21.enums.Genero;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Tag(name = "Participante Controller", description = "Controladores relacionados a operações com participantes.")
public class ParticipanteController {

    ParticipanteRepository participanteRepository;
    RespostaRepository respostaRepository;
    ParticipanteController(ParticipanteRepository participanteRepository, RespostaRepository respostaRepository){
        this.participanteRepository = participanteRepository;
        this.respostaRepository = respostaRepository;
    }
    @PostMapping("/participante")
    @Operation(summary = "Criar novo Participante", description = "Cria um novo participante na pesquisa.")
    @ApiResponse(responseCode = "200", description = "Sucesso quando um novo Participante é criado", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "422", description = "Não foi possível criar um novo Participante", content = @Content(mediaType = "application/json"))
    public ResponseEntity<Participante> criar(@Valid @RequestBody ParticipanteDTO request) {
        Participante participante = new Participante();
        participante.setNome(request.getNome());
        participante.setIdade(request.getIdade());
        if( request.getGenero().equals("Masculino") ){
            participante.setGenero( Genero.MASCULINO );
        } else {
            participante.setGenero( Genero.FEMININO );
        }
        CriarParticipanteUseCase criarParticipanteUseCase = new CriarParticipanteUseCase(this.participanteRepository);
        Participante response = criarParticipanteUseCase.execute( participante );
        if( response.getId() > 0 )
        {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.unprocessableEntity().build(); //422
        }
    }

    @PutMapping("/participante/{id}")
    @Operation(summary = "Editar  participante e resposta na pesquisa", description = "Edita um participante e sua resposta na pesquisa.")
    @ApiResponse(responseCode = "200", description = "Sucesso quando um novo Participante é editado", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Não foi possível encontrar um participante para edição.", content = @Content(mediaType = "application/json"))
    public ResponseEntity<Map<String, Map<String, Object>>> editar(@Valid @RequestBody ParticipanteEdicaoDTO request, @PathVariable("id") int id) {
        if ( id > 0)
        {
            Participante participante = new Participante();
            participante.setId(id);
            participante.setNome(request.getNome());
            participante.setIdade(request.getIdade());
            participante.setGenero(Genero.MASCULINO);
            if( request.getGenero().equals("Feminino"))
            {
                participante.setGenero(Genero.FEMININO);
            }

            EditarParticipanteUseCase editarParticipanteUseCase = new EditarParticipanteUseCase(this.participanteRepository, this.respostaRepository);

            var ansiedade = request.getPontuacao_total_ansiedade();
            var depressao = request.getPontuacao_total_depressao();
            var estresse = request.getPontuacao_total_estresse();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dataHora = LocalDateTime.parse(request.getDataResposta(), formatter);

            Participante response = editarParticipanteUseCase.execute( participante, ansiedade, depressao, estresse, dataHora );
            if( response != null )
            {
                var listarParticipantePontosEscalaByIdUseCase = new ListarParticipantePontosEscalaByIdUseCase(this.participanteRepository, this.respostaRepository);
                var listarParticipante = listarParticipantePontosEscalaByIdUseCase.execute(id);
                return ResponseEntity.ok(listarParticipante);
            } else {
                return ResponseEntity.notFound().build(); //404
            }
        } else {
            return ResponseEntity.badRequest().build(); //400
        }
    }
}
