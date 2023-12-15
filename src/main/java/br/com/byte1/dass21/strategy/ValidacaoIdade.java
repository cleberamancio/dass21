package br.com.byte1.dass21.strategy;

public class ValidacaoIdade {
    private ValidacaoIdadeStrategyInterface estrategiaValidacao;
    public ValidacaoIdade(ValidacaoIdadeStrategyInterface estrategiaValidacao)
    {
        this.estrategiaValidacao = estrategiaValidacao;
    }

    public void validate(int idade)
    {
        this.estrategiaValidacao.validar(idade);
    }
}