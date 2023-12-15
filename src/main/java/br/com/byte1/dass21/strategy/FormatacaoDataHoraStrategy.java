package br.com.byte1.dass21.strategy;

import java.time.LocalDateTime;

public class FormatacaoDataHoraStrategy {
    private DataHoraStrategyInterface estrategia;
    public FormatacaoDataHoraStrategy(DataHoraStrategyInterface estrategia)
    {
        this.estrategia = estrategia;
    }

    public String format(LocalDateTime dataHora)
    {
        return this.estrategia.format(dataHora);
    }
}