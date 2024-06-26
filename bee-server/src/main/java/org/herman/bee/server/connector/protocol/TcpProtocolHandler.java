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
import org.herman.bee.server.connector.protocol.processor.TcpProcessor;

import java.net.InetSocketAddress;

public class TcpProtocolHandler extends LifecycleBase implements ProtocolHandler {
    private final Endpoint endpoint;
    private final TcpProcessor processor;

    public TcpProtocolHandler(Engine engine, int port) {
        this.endpoint = new TcpEndpoint(new InetSocketAddress(port), this);
        this.processor = new TcpProcessor(engine, port);
    }

    @Override
    public Protocol getProtocol() {
        return Protocol.Tcp;
    }

    @Override
    public void configPipeline(ChannelPipeline pipeline) {
        pipeline.addLast(processor);
    }

    @Override
    protected void startInternal() {
        endpoint.start();
    }

    @Override
    protected void stopInternal() {
        endpoint.stop();
    }

    @Override
    public void setBossThreadCount(int bossThreadCount) {
        this.endpoint.setBossThreadCount(bossThreadCount);
    }

    @Override
    public void setWorkerThreadCount(int workerThreadCount) {
        this.endpoint.setWorkerThreadCount(workerThreadCount);
    }
}
