package br.com.byte1.dass21.strategy;

public class ValidacaoNomeParticipante implements ValidacaoNomeStrategyInterface{

    private static final int TAMANHO_MAXIMO_NOME = 100;

    @Override
    public void validar(String nome) {
        validaNomeNaoNulo(nome);
        validaTamanhoNome(nome);
    }

    private void validaNomeNaoNulo(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
    }

    private void validaTamanhoNome(String nome) {
        if (nome.length() > TAMANHO_MAXIMO_NOME) {
            throw new IllegalArgumentException("Nome não pode ter mais de " + TAMANHO_MAXIMO_NOME + " caracteres.");
        }
    }
}
