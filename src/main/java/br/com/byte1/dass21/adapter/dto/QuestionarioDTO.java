package br.com.byte1.dass21.adapter.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class QuestionarioDTO {
    @Min(value = 1, message = "Participante não definido")
    private int participante_id;

    @Min(value = 0, message = "Insira a pontuação total para Depressão!")
    private int pontuacao_total_depressao;

    @Min(value = 0, message = "Insira a pontuação total para Ansiedade!")
    private int pontuacao_total_ansiedade;

    @Min(value = 0, message = "Insira a pontuação total para Estresse!")
    private int pontuacao_total_estresse;
}