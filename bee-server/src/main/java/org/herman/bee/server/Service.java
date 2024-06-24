package org.herman.bee.server;

import org.herman.bee.common.Container;
import org.herman.bee.server.connector.Connector;

public interface Service extends Container {

    void addConnector(Connector connector);

    Engine getEngine();

}
