package br.edu.infnet.votingapi.domain.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(final String msg) {
        super(msg);
    }
}
