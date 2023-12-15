package br.com.byte1.dass21.adapter.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ParticipanteDTO {
    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;

    @Min(value = 0, message = "A idade deve ser maior que zero")
    private int idade;

    @NotBlank(message = "O Genero deve ser definido")
    private String genero;
}