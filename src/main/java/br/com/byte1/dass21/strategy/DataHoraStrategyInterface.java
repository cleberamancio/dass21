package br.com.byte1.dass21.strategy;

import java.time.LocalDateTime;

public interface DataHoraStrategyInterface {
    String format(LocalDateTime dataHora);
}