package cc.coopersoft.keycloak.phone.providers.sender.exceptions;

import java.io.IOException;

public class UnsupportedEncodingException extends IOException {

@java.io.Serial
private static final long serialVersionUID = -4274276298326136670L;

/**
 * Constructs an UnsupportedEncodingException without a detail message.
 */
public UnsupportedEncodingException() {
    super();
}

/**
 * Constructs an UnsupportedEncodingException with a detail message.
 * @param s Describes the reason for the exception.
 */
public UnsupportedEncodingException(String s) {
    super(s);
}
}
