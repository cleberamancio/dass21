package br.com.byte1.dass21.entity;

import br.com.byte1.dass21.strategy.ValidacaoIdade;
import br.com.byte1.dass21.strategy.ValidacaoIdadeParticipante;
import br.com.byte1.dass21.strategy.ValidacaoNome;
import br.com.byte1.dass21.strategy.ValidacaoNomeParticipante;
import br.com.byte1.dass21.enums.Genero;

public class Participante {

    private int id = 0;
    private String nome;
    private int idade;
    private String genero;

    // Construtor e outros métodos...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        new ValidacaoNome(new ValidacaoNomeParticipante()).validate(nome);
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        new ValidacaoIdade(new ValidacaoIdadeParticipante()).validate(idade);
        this.idade = idade;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        if( genero != null ) {
            this.genero = genero.getDescricao();
        } else {
            throw new IllegalArgumentException("O Participante precisa ter um Genero");
        }
    }
}
