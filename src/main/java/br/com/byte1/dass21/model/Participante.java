package br.com.byte1.dass21.model;

import br.com.byte1.dass21.enums.Genero;
import br.com.byte1.dass21.strategy.ValidacaoIdade;
import br.com.byte1.dass21.strategy.ValidacaoIdadeParticipante;
import br.com.byte1.dass21.strategy.ValidacaoNome;
import br.com.byte1.dass21.strategy.ValidacaoNomeParticipante;
import jakarta.persistence.*;

@Entity
@Table(name = "Participantes")
public class Participante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "idade", nullable = false)
    private int idade;

    @Column(name = "genero", nullable = false, length = 20)
    private String genero;

    public Participante(){}

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
