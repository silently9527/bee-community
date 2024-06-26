package org.herman.bee.server.connector;

import cn.hutool.core.lang.Assert;
import lombok.Getter;
import lombok.Setter;
import org.herman.bee.common.LifecycleBase;
import org.herman.bee.server.Service;
import org.herman.bee.server.connector.protocol.Protocol;
import org.herman.bee.server.connector.protocol.ProtocolHandler;
import org.herman.bee.server.connector.protocol.TcpProtocolHandler;

public class Connector extends LifecycleBase {
    @Setter
    @Getter
    private Service service;
    private ProtocolHandler protocolHandler;
    private Protocol protocol;
    private int port;

    public void Connector(Protocol protocol, int port) {
        this.protocol = protocol;
        this.port = port;
    }

    @Override
    protected void startInternal() {
        if (Protocol.Tcp.equals(protocol)) {
            this.protocolHandler = new TcpProtocolHandler(service.getEngine(), port);
        }
        Assert.notNull(this.protocolHandler);
        protocolHandler.start();
    }

    @Override
    protected void stopInternal() {
        protocolHandler.stop();
    }

    public void setBossThreadCount(int bossThreadCount) {
        this.protocolHandler.setBossThreadCount(bossThreadCount);
    }

    public void setWorkerThreadCount(int workerThreadCount) {
        this.protocolHandler.setWorkerThreadCount(workerThreadCount);
    }
}
