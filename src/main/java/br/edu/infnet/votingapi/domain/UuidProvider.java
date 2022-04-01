package br.edu.infnet.votingapi.domain;

import java.util.UUID;

public class UuidProvider {
    public static String get() {
        return UUID.randomUUID().toString();
    }
}
