package org.herman.bee.server.sevice;

import org.herman.bee.common.ContainerBase;
import org.herman.bee.server.Engine;
import org.herman.bee.server.Service;
import org.herman.bee.server.connector.Connector;

public class StandardService extends ContainerBase implements Service {
    @Override
    protected void startInternal() {

    }

    @Override
    protected void stopInternal() {

    }

    @Override
    public void addConnector(Connector connector) {

    }

    @Override
    public Engine getEngine() {
        return null;
    }
}
