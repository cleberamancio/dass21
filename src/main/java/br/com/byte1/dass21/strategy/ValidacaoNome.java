package br.com.byte1.dass21.strategy;

public class ValidacaoNome {
    private ValidacaoNomeStrategyInterface estrategiaValidacao;
    public ValidacaoNome(ValidacaoNomeStrategyInterface estrategiaValidacao)
    {
        this.estrategiaValidacao = estrategiaValidacao;
    }

    public void validate(String nome)
    {
        this.estrategiaValidacao.validar(nome);
    }
}