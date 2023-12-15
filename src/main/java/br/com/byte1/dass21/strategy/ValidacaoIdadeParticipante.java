package br.com.byte1.dass21.strategy;

public class ValidacaoIdadeParticipante implements ValidacaoIdadeStrategyInterface{

    @Override
    public void validar(int idade) {
        validaIdadeMaiorQueZero(idade);
    }

    private void validaIdadeMaiorQueZero(int idade) {
        if (idade <= 0) {
            throw new IllegalArgumentException("Idade não pode ser negativa.");
        }
    }
}
