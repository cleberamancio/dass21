package br.com.byte1.dass21.strategy;

public class ValidacaoPontuacaoResposta implements ValidacaoIdadeStrategyInterface{

    @Override
    public void validar(int pontuacao) {
        validaPontuacaoIdadeMaiorQueZero(pontuacao);
    }

    private void validaPontuacaoIdadeMaiorQueZero(int pontuacao) {
        if (pontuacao < 0) {
            throw new IllegalArgumentException("Idade não pode ser negativa.");
        }
    }
}
