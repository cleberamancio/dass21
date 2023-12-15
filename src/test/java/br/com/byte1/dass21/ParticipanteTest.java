package br.com.byte1.dass21;

import br.com.byte1.dass21.entity.Participante;
import br.com.byte1.dass21.enums.Genero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ParticipanteTest {
    Participante participante;
    @BeforeEach
    void beforeEach(){
        participante = new Participante();
        participante.setIdade(23);
        participante.setGenero(Genero.MASCULINO);
    }

    @Test
    @DisplayName("Testa se retorna uma Exceção quando um participante tiver um nome nulo ou vazio.")
    void test_se_retorna_excecao_quando_um_participante_tiver_nome_nulo_ou_vazio()
    {
        assertThrows(IllegalArgumentException.class, () -> {
            participante.setNome("");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            participante.setNome(null);
        });
    }

    @Test
    @DisplayName("Testa se retorna uma Exceção quando um participante tiver um nome com mais de 100 caracteres.")
    void test_se_retorna_excecao_quando_um_participante_tiver_nome_com_mais_de_100_caracteres()
    {
        String nome = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas vel sapien sit amet. Maecenas velkio";
        assertThrows(IllegalArgumentException.class, () -> {
            participante.setNome(nome);
        });
    }

    @Test
    @DisplayName("Testa se retorna uma Exceção quando a idade de um participante for menor que zero.")
    void test_se_retorna_excecao_quando_idade_do_participante_menor_que_zero(){
        assertThrows(IllegalArgumentException.class, () -> {
            participante.setIdade(-10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            participante.setIdade(0);
        });
    }

    @Test
    @DisplayName("Testa se retorna uma Exceção quando o Genero do participante não for passado.")
    void test_se_retorna_excecao_quando_genero_do_participante_for_nulo(){
        assertThrows(IllegalArgumentException.class, () -> {
            participante.setGenero(null);
        });

    }
}
