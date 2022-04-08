package br.edu.infnet.votingapi.infrastructure.util;

import java.time.LocalDateTime;

public class LocalDateTimeProvider {
    public static LocalDateTime get() {
        return LocalDateTime.now();
    }
}
