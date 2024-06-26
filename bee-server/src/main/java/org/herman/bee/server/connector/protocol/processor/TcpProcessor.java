package org.herman.bee.server.connector.protocol.processor;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.herman.bee.common.utils.Constants;
import org.herman.bee.server.Engine;
import org.herman.bee.server.connector.request.Request;
import org.herman.bee.server.connector.request.TcpRequest;
import org.herman.bee.server.connector.response.Response;
import org.herman.bee.server.connector.response.TcpResponse;

public class TcpProcessor extends AbstractProcessor {
    private final int port;

    public TcpProcessor(Engine engine, int port) {
        super(engine);
        this.port = port;
    }

    @Override
    public void process(Request request, Response response) {
        engine.invoke(request, response);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        TcpRequest request = TcpRequest.builder()
                .domain(Constants.localhost)
                .port(port)
                .msg((ByteBuf) msg)
                .build();
        TcpResponse response = TcpResponse.builder()
                .channel(ctx.channel())
                .build();
        this.process(request, response);
    }
}
