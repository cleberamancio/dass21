package br.com.byte1.dass21;

import br.com.byte1.dass21.application.CriarParticipanteUseCase;
import br.com.byte1.dass21.application.SalvarRespostaUseCase;
import br.com.byte1.dass21.domain.ParticipanteRepository;
import br.com.byte1.dass21.domain.RespostaRepository;
import br.com.byte1.dass21.entity.Participante;
import br.com.byte1.dass21.entity.Resposta;
import br.com.byte1.dass21.enums.Genero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class InsertDatabaseTest {
    private SalvarRespostaUseCase salvarService;
    private RespostaRepository respostaRepository;
    private ParticipanteRepository participanteRepository;

    private CriarParticipanteUseCase criarParticipanteService;

    private Resposta respostaRetorno;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        respostaRepository = Mockito.mock(RespostaRepository.class);
        salvarService = new SalvarRespostaUseCase(respostaRepository);

        Participante participanteRetorno = new Participante();
        participanteRetorno.setNome("Daniel Souza Fraga");
        participanteRetorno.setIdade(23);
        participanteRetorno.setGenero(Genero.MASCULINO);

        respostaRetorno = new Resposta();
        respostaRetorno.setId(1);
        respostaRetorno.setParticipante(participanteRetorno);
        respostaRetorno.setDataResposta(LocalDateTime.now());
        respostaRetorno.setPontuacaoTotalDepressao(4);
        respostaRetorno.setPontuacaoTotalAnsiedade(7);
        respostaRetorno.setPontuacaoTotalEstresse(17);
    }

    @Test
    @DisplayName("Testa se um participante foi inserido.")
    void test_se_participante_foi_inserido(){
        Participante participanteRetorno = new Participante();
        participanteRetorno.setId(1);
        participanteRetorno.setNome("Daniel Souza Fraga");
        participanteRetorno.setIdade(23);
        participanteRetorno.setGenero(Genero.MASCULINO);

        participanteRepository = Mockito.mock(ParticipanteRepository.class);
        criarParticipanteService = new CriarParticipanteUseCase(participanteRepository);

        Mockito.when(criarParticipanteService.execute(any(Participante.class))).thenReturn(participanteRetorno);

        Participante participante = new Participante();
        participante.setNome("Daniel Souza Fraga");
        participante.setIdade(23);
        participante.setGenero(Genero.MASCULINO);

        Participante participanteRetornado = criarParticipanteService.execute(participante);

        assertEquals(participanteRetornado.getId(), 1);
    }

    @Test
    @DisplayName("Testa se um participante sem Nome não é inserido")
    void test_se_um_participante_sem_nome_nao_e_inserido(){
        assertThrows(java.lang.NullPointerException.class, () -> {
            Participante participante = new Participante();
            participante.setIdade(23);
            participante.setGenero(Genero.MASCULINO);
            criarParticipanteService.execute(participante);
        });
    }

    @Test
    @DisplayName("Testa se um participante sem Idade não é inserido")
    void test_se_um_participante_sem_idade_nao_e_inserido(){
        assertThrows(java.lang.NullPointerException.class, () -> {
            Participante participante = new Participante();
            participante.setNome("Daniel Souza Fraga");
            participante.setGenero(Genero.MASCULINO);
            criarParticipanteService.execute(participante);
        });
    }

    @Test
    @DisplayName("Testa se um participante sem Gênero não é inserido")
    void test_se_um_participante_sem_genero_nao_e_inserido(){
        assertThrows(java.lang.NullPointerException.class, () -> {
            Participante participante = new Participante();
            participante.setNome("Daniel Souza Fraga");
            participante.setIdade(23);
            criarParticipanteService.execute(participante);
        });
    }

    @Test
    @DisplayName("Retorna uma Exceção quando alguma pontiação receber um valor menor que zero.")
    void test_se_retorna_excecao_quando_alguma_pontuacao_receber_um_valor_menor_que_zero()
    {
        Participante participante = new Participante();
        participante.setNome("Daniel Souza Fraga");
        participante.setIdade(23);
        participante.setGenero(Genero.MASCULINO);

        Resposta resposta = new Resposta();
        resposta.setParticipante(participante);

        resposta.setDataResposta(LocalDateTime.now());

        assertThrows(IllegalArgumentException.class, () -> {
            resposta.setPontuacaoTotalDepressao(-1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            resposta.setPontuacaoTotalAnsiedade(-1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            resposta.setPontuacaoTotalEstresse(-1);
        });
    }

    @Test
    @DisplayName("Testa restorna um objeto do tipo Resposta ao salvar.")
    void test_se_uma_resposta_com_um_id_e_retornado_ao_salvar(){

        Mockito.when(salvarService.execute(any(Resposta.class))).thenReturn(respostaRetorno);

        Participante participante = new Participante();
        participante.setNome("Daniel Souza Fraga");
        participante.setIdade(23);
        participante.setGenero(Genero.MASCULINO);

        Resposta resposta = new Resposta();
        resposta.setParticipante(participante);
        resposta.setDataResposta(LocalDateTime.now());
        resposta.setPontuacaoTotalDepressao(4);
        resposta.setPontuacaoTotalAnsiedade(7);
        resposta.setPontuacaoTotalEstresse(17);

        Resposta respostaRetornada = salvarService.execute(resposta);
        assertEquals(respostaRetornada.getId(), 1);
    }

    @Test
    @DisplayName("Testa se uma resposta não é salva se uma Pontuação Total de Depressão não for inserida")
    void test_se_uma_resposta_nao_e_salva_se_um_pontuacao_total_depressao_nao_for_inserida(){

        Mockito.when(salvarService.execute(any(Resposta.class))).thenReturn(respostaRetorno);

        Participante participante = new Participante();
        participante.setNome("Daniel Souza Fraga");
        participante.setIdade(23);
        participante.setGenero(Genero.MASCULINO);

        Resposta resposta = new Resposta();
        resposta.setParticipante(participante);
        resposta.setDataResposta(LocalDateTime.now());
        resposta.setPontuacaoTotalDepressao(0);
        resposta.setPontuacaoTotalAnsiedade(7);
        resposta.setPontuacaoTotalEstresse(17);

        Resposta respostaRetornada = salvarService.execute(resposta);
        assertEquals(respostaRetornada.getId(), 1);
    }
}
