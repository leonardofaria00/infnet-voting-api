package br.edu.infnet.votingapi.domain.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(final String message) {
        super(message);
    }
}
