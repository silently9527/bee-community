package org.herman.bee.server.connector.request;

import org.herman.bee.server.connector.protocol.Protocol;

import java.util.Locale;

public interface Request {
    Protocol getProtocol();

    String getDomain();

    int getPort();

    default String getURL() {
        return String.format("%s://%s:%s", getProtocol().name().toLowerCase(Locale.ROOT), getDomain(), getPort());
    }
}
