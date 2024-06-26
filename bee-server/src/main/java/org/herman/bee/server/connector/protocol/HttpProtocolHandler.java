package org.herman.bee.server.connector.protocol;

import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpServerExpectContinueHandler;
import io.netty.handler.codec.http.HttpServerKeepAliveHandler;
import org.herman.bee.common.LifecycleBase;
import org.herman.bee.server.Engine;
import org.herman.bee.server.connector.endpoint.Endpoint;
import org.herman.bee.server.connector.endpoint.TcpEndpoint;
import org.herman.bee.server.connector.protocol.processor.HttpProcessor;
import org.herman.bee.server.connector.protocol.processor.Processor;

import java.net.InetSocketAddress;

public class HttpProtocolHandler extends LifecycleBase implements ProtocolHandler {
    private Endpoint endpoint;
    private Processor processor;

    public HttpProtocolHandler(Engine engine, int port) {
        this.endpoint = new TcpEndpoint(new InetSocketAddress(port), this);
        this.processor = new HttpProcessor(engine);
    }

    @Override
    public Protocol getProtocol() {
        return Protocol.Http;
    }

    @Override
    public void configPipeline(ChannelPipeline pipeline) {
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(65536));
        pipeline.addLast(new HttpServerExpectContinueHandler());
        pipeline.addLast(new HttpServerKeepAliveHandler());
    }


    @Override
    protected void startInternal() {
        endpoint.start();
    }

    @Override
    protected void stopInternal() {
        endpoint.stop();
    }

    public void setBossThreadCount(int bossThreadCount) {
        this.endpoint.setBossThreadCount(bossThreadCount);
    }

    public void setWorkerThreadCount(int workerThreadCount) {
        this.endpoint.setWorkerThreadCount(workerThreadCount);
    }
}
