package br.com.byte1.dass21.strategy;

public class EscalaEstresse implements EscalasStrategyInterface{

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
            case 0, 1, 2, 3, 4, 5, 6, 7 -> "0 - Sem sintomas";
            case 8,9 -> "1 - Sintomas leves";
            case 10,12 -> "2 - Sintomas moderados";
            case 13, 14, 15,16 -> "3 - Sintomas graves";
            default -> {
                yield "4 - Sintomas muito graves";
            }
        };
    }
}
