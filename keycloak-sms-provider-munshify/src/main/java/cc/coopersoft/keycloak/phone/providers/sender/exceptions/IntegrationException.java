package cc.coopersoft.keycloak.phone.providers.sender.exceptions;


public class IntegrationException extends BaseException {

    private static final long serialVersionUID = 126621127721L;

    /**
     * Initialising Constructor.
     *
     * @param message
     *            message
     * @param exception
     *            exception
     */
    public IntegrationException(final String message, final Exception exception) {
        super(message, exception);
    }

    /**
     * Initialising Constructor.
     *
     * @param message
     *            message
     */
    public IntegrationException(final String message) {
        super(message);
    }
}
