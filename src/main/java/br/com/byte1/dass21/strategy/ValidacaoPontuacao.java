package br.com.byte1.dass21.strategy;

public class ValidacaoPontuacao {
    private ValidacaoIdadeStrategyInterface estrategiaValidacao;
    public ValidacaoPontuacao(ValidacaoIdadeStrategyInterface estrategiaValidacao)
    {
        this.estrategiaValidacao = estrategiaValidacao;
    }

    public void validate(int pontuacao)
    {
        this.estrategiaValidacao.validar(pontuacao);
    }
}