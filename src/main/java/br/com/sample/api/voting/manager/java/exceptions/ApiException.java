package br.com.sample.api.voting.manager.java.exceptions;

public class ApiException extends RuntimeException {
    private static final long serialVersionUID = -8863507275894675502L;

    public ApiException(String messageFormat, Object... messageValues) {
        super(String.format(messageFormat, messageValues));
    }

    public ApiException(Throwable throwable, String messageFormat, Object... messageValues) {
        super(String.format(messageFormat, messageValues), throwable);
    }
}
