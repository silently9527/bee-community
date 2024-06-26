package org.herman.bee.server.engine.mapping;

import lombok.Builder;
import org.herman.bee.server.connector.protocol.Protocol;

import java.util.Locale;

@Builder
public class MappingInfo {
    private final Protocol protocol;
    private final String domain;
    private final int port;


    @Override
    public String toString() {
        // "http://localhost:8080" "tcp://localhost:8080" "udp://localhost:8080"
        return String.format("%s://%s:%s", protocol.name().toLowerCase(Locale.ROOT), domain, port);
    }
}
