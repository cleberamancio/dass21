package br.com.byte1.dass21.strategy;

public class EscalaStrategy {
    private EscalasStrategyInterface estrategia;
    public EscalaStrategy(EscalasStrategyInterface estrategia)
    {
        this.estrategia = estrategia;
    }

    public String escala(int pontuacao)
    {
        return this.estrategia.escala(pontuacao);
    }
}