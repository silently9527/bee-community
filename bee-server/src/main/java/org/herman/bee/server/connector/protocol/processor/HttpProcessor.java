package org.herman.bee.server.connector.protocol.processor;

import io.netty.channel.ChannelHandlerContext;
import org.herman.bee.server.Engine;
import org.herman.bee.server.connector.request.Request;
import org.herman.bee.server.connector.response.Response;

public class HttpProcessor extends AbstractProcessor {


    public HttpProcessor(Engine engine) {
        super(engine);
    }

    @Override
    public void process(Request request, Response response) {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


    }
}
