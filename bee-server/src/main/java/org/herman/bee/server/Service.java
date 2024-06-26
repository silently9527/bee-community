package org.herman.bee.server;

import org.herman.bee.common.Lifecycle;
import org.herman.bee.server.connector.Connector;

public interface Service extends Lifecycle {

    void addConnector(Connector connector);

    Engine getEngine();

}
