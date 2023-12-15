package br.com.byte1.dass21.strategy;

public class EscalaDeprecao implements EscalasStrategyInterface{
    @Override
    public String escala(int pontuacao) {
        return switch (pontuacao) {
            case 0, 1, 2, 3-> "0 - Sem sintomas";
            case 4,5 -> "1 - Sintomas leves";
            case 6,7 -> "2 - Sintomas moderados";
            case 8, 9 -> "3 - Sintomas muito graves";
            default -> {
                yield "4 - Sintomas muito graves";
            }
        };
    }
}
