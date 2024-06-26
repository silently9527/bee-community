package org.herman.bee.server.connector.request;

import io.netty.buffer.ByteBuf;
import lombok.Builder;
import lombok.Getter;
import org.herman.bee.server.connector.protocol.Protocol;

@Builder
public class TcpRequest implements Request {
    private int port;
    private String domain;
    @Getter
    private ByteBuf msg;

    @Override
    public Protocol getProtocol() {
        return Protocol.Tcp;
    }

    @Override
    public String getDomain() {
        return domain;
    }

    @Override
    public int getPort() {
        return port;
    }

}
