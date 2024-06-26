package org.herman.bee.server.connector.response;

import io.netty.channel.Channel;
import lombok.Builder;

@Builder
public class TcpResponse implements Response {
    private Channel channel;
}
