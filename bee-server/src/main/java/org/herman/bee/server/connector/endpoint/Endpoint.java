package org.herman.bee.server.connector.endpoint;

import org.herman.bee.common.Lifecycle;
import org.herman.bee.server.connector.protocol.ProtocolHandler;

public interface Endpoint extends Lifecycle {

    ProtocolHandler protocolHandler();

    void setBossThreadCount(int bossThreadCount);

    void setWorkerThreadCount(int workerThreadCount);
}
