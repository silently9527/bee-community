package org.herman.bee.server.connector.endpoint;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.herman.bee.common.LifecycleBase;
import org.herman.bee.server.connector.protocol.ProtocolHandler;

import java.net.InetSocketAddress;
import java.util.Objects;

@Slf4j
public class TcpEndpoint extends LifecycleBase implements Endpoint {
    private static final int DEFAULT_BOSS_THREAD_COUNT = 1;
    private static final int DEFAULT_WORKER_THREAD_COUNT = 3;
    private int bossThreadCount = DEFAULT_BOSS_THREAD_COUNT;
    private int workerThreadCount = DEFAULT_WORKER_THREAD_COUNT;
    private final InetSocketAddress bindAddress;
    private final ProtocolHandler protocolHandler;

    private ServerBootstrap bootstrap;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    private Channel channel;

    public TcpEndpoint(InetSocketAddress bindAddress, ProtocolHandler protocolHandler) {
        this.bindAddress = bindAddress;
        this.protocolHandler = protocolHandler;
    }

    @Override
    protected void startInternal() {
        log.info("start endpoint bind, address:{}", bindAddress);
        bootstrap = new ServerBootstrap();

        bossGroup = new NioEventLoopGroup(bossThreadCount);
        workerGroup = new NioEventLoopGroup(workerThreadCount);

        initServerBootstrap();

        // bind
        try {
            ChannelFuture channelFuture = bootstrap.bind(bindAddress);
            channelFuture.syncUninterruptibly();
            channel = channelFuture.channel();
        } catch (Exception t) {
            closeBootstrap();
            throw t;
        }
        log.info("endpoint bind successful, address:{}", bindAddress);
    }

    private void initServerBootstrap() {
        bootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_REUSEADDR, Boolean.TRUE)
                .childOption(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        protocolHandler.configPipeline(ch.pipeline());
                    }
                });
    }


    @Override
    protected void stopInternal() {
        try {
            if (Objects.nonNull(channel)) {
                channel.close();
            }
            closeBootstrap();
            log.info("endpoint stop successful, address:{}", bindAddress);
        } catch (Exception e) {
            log.warn("stop endpoint error", e);
        }
    }

    private void closeBootstrap() {
        try {
            if (bootstrap != null) {
                Future<?> bossGroupShutdownFuture = bossGroup.shutdownGracefully();
                Future<?> workerGroupShutdownFuture = workerGroup.shutdownGracefully();
                bossGroupShutdownFuture.syncUninterruptibly();
                workerGroupShutdownFuture.syncUninterruptibly();
            }
        } catch (Exception e) {
            log.warn("stop server bootstrap error", e);
        }
    }

    @Override
    public ProtocolHandler protocolHandler() {
        return protocolHandler;
    }

    @Override
    public void setBossThreadCount(int bossThreadCount) {
        this.bossThreadCount = bossThreadCount;
    }

    @Override
    public void setWorkerThreadCount(int workerThreadCount) {
        this.workerThreadCount = workerThreadCount;
    }
}
