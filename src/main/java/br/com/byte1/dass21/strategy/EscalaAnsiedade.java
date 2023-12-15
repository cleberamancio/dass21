package br.com.byte1.dass21.strategy;

public class EscalaAnsiedade implements EscalasStrategyInterface{

/*
0 - Sem sintomas
1 - Sintomas leves
2 - Sintomas moderados
3 - Sintomas graves
4 - Sintomas muito graves
*/
    @Override
    public String escala(int pontuacao) {
        return switch (pontuacao) {
            case 0, 1, 2, 3, 4 -> "0 - Sem sintomas";
            case 5,6 -> "1 - Sintomas leves";
            case 7,10 -> "2 - Sintomas moderados";
            case 11, 13 -> "3 - Sintomas graves";
            default -> {
                yield "4 - Sintomas muito graves";
            }
        };
    }
}
