package cc.coopersoft.keycloak.phone.providers.sender.exceptions;

public class BaseException extends Exception {

    private static final long serialVersionUID = 12211L;
    private final String exceptionMessage;
    private static final String NO_EXCEPTION_MESSAGE = "NO_EXCEPTION_MESSAGE";

    public BaseException(final String exceptionMessage, final Exception exception) {
        super(exceptionMessage, exception);
        this.exceptionMessage = exceptionMessage;
        this.initCause(exception);
    }

    public BaseException() {
        super(NO_EXCEPTION_MESSAGE);
        this.exceptionMessage = NO_EXCEPTION_MESSAGE;
    }

    public BaseException(final String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionMessage = exceptionMessage;
    }

    public String getMessage() {
        return this.exceptionMessage;
    }

}
