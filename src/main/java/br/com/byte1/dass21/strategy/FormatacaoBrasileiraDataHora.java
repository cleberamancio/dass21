package br.com.byte1.dass21.strategy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatacaoBrasileiraDataHora implements DataHoraStrategyInterface{
    @Override
    public String format(LocalDateTime dataHora) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dataHora.format(dateTimeFormatter);
    }
}
