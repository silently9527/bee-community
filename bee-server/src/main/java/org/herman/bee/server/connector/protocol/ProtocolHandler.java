package org.herman.bee.server.connector.protocol;

import io.netty.channel.ChannelPipeline;
import org.herman.bee.common.Lifecycle;

public interface ProtocolHandler extends Lifecycle {

    Protocol getProtocol();

    void configPipeline(ChannelPipeline pipeline);

    void setBossThreadCount(int bossThreadCount);

    void setWorkerThreadCount(int workerThreadCount);
}
