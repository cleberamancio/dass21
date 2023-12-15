package br.com.byte1.dass21.adapter.controller;

import br.com.byte1.dass21.adapter.dto.QuestionarioDTO;
import br.com.byte1.dass21.application.ListarParticipantePontosEscalaByIdUseCase;
import br.com.byte1.dass21.application.ListarParticipantePontosEscalaUseCase;
import br.com.byte1.dass21.application.SalvarRespostaUseCase;
import br.com.byte1.dass21.domain.ParticipanteRepository;
import br.com.byte1.dass21.domain.RespostaRepository;
import br.com.byte1.dass21.entity.Resposta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Tag(name = "Pesquisa Controller", description = "Controladores relacionados a operações de pesquisa.")
public class PesquisaController {

    RespostaRepository respostaRepository;
    ParticipanteRepository participanteRepository;
    public PesquisaController(RespostaRepository respostaRepository, ParticipanteRepository participanteRepository){
        this.respostaRepository = respostaRepository;
        this.participanteRepository = participanteRepository;
    }

    @GetMapping("/pesquisa")
    @Operation(summary = "Exiber pesquisa", description = "Exibe a lista com as informações de todos os participantes.")
    @ApiResponse(responseCode = "200", description = "Lista todos os Participantes cadastrados e suas respostas na pesquisa.", content = @Content(mediaType = "application/json"))
    public ResponseEntity<Map> list(){
        var listarParticipantePontosEscalaUseCase = new ListarParticipantePontosEscalaUseCase(this.participanteRepository, this.respostaRepository);
        var listarParticipante = listarParticipantePontosEscalaUseCase.execute();
        return ResponseEntity.ok(listarParticipante);
    }

    @GetMapping("/pesquisa/{participante_id}")
    @Operation(summary = "Exibe pesquisa de um participante em específico.", description = "Exibe as informações de um participante específico.")
    @ApiResponse(responseCode = "200", description = "Lista todos os dados de um Participantes cadastrados e sua resposta na pesquisa.", content = @Content(mediaType = "application/json"))
    public ResponseEntity<Map<String, Map<String, Object>>> listById(@PathVariable("participante_id") int participante_id)
    {
        var listarParticipantePontosEscalaByIdUseCase = new ListarParticipantePontosEscalaByIdUseCase(this.participanteRepository, this.respostaRepository);
        var listarParticipante = listarParticipantePontosEscalaByIdUseCase.execute(participante_id);
        return ResponseEntity.ok(listarParticipante);
    }

    @PostMapping("/pesquisa")
    @Operation(summary = "Salvar resposta da pesquisa.", description = "Salva a resposta de um Participante na pesquisa.")
    @ApiResponse(responseCode = "200", description = "Salva a resposta de um Participante na pesquisa.", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "422", description = "Somente é possivel 1 respoata por Participante.", content = @Content(mediaType = "application/json"))
    public ResponseEntity<Resposta> salvar(@Valid @RequestBody QuestionarioDTO request) {
        int participante_id = request.getParticipante_id();
        int pontuacao_total_ansiedade  = request.getPontuacao_total_ansiedade();
        int pontuação_total_depressao = request.getPontuacao_total_depressao();
        int pontuacao_total_estresse  = request.getPontuacao_total_estresse();

        Resposta resposta = new Resposta();
        resposta.setDataResposta(LocalDateTime.now());
        resposta.setPontuacaoTotalAnsiedade(pontuacao_total_ansiedade);
        resposta.setPontuacaoTotalDepressao(pontuação_total_depressao);
        resposta.setPontuacaoTotalEstresse(pontuacao_total_estresse);

        var salvarRespostaUseCase = new SalvarRespostaUseCase(this.respostaRepository);
        Resposta respostaRetornada = salvarRespostaUseCase.execute( resposta , participante_id);

        if( respostaRetornada != null && respostaRetornada.getId() > 0) {
            return ResponseEntity.ok(respostaRetornada);
        }
        return ResponseEntity.unprocessableEntity().build(); //422
    }
}
