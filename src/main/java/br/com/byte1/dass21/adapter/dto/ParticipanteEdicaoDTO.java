package br.com.byte1.dass21.adapter.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class ParticipanteEdicaoDTO {
    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;

    @Min(value = 0, message = "A idade deve ser maior que zero")
    private int idade;

    @NotBlank(message = "O Genero deve ser definido")
    private String genero;

    @Min(value = 0, message = "Insira a pontuação total para Depressão!")
    private int pontuacao_total_depressao;

    @Min(value = 0, message = "Insira a pontuação total para Ansiedade!")
    private int pontuacao_total_ansiedade;

    @Min(value = 0, message = "Insira a pontuação total para Estresse!")
    private int pontuacao_total_estresse;
    @NotNull(message = "A data da resposta não pode ser nula")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // Ajuste o padrão conforme necessário
    private String dataResposta;
}