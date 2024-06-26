package org.herman.bee.server.sevice;

import org.herman.bee.common.LifecycleBase;
import org.herman.bee.server.Engine;
import org.herman.bee.server.Server;
import org.herman.bee.server.Service;
import org.herman.bee.server.connector.Connector;
import org.herman.bee.server.engine.StandardEngine;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class StandardService extends LifecycleBase implements Service {
    private final Engine engine = new StandardEngine();
    private final List<Connector> connectors = new CopyOnWriteArrayList<>();

    @Override
    protected void startInternal() {
        engine.start();
        connectors.forEach(connector -> {
            connector.setService(this);
            connector.start();
        });
    }

    @Override
    protected void stopInternal() {
        engine.stop();
    }

    @Override
    public void addConnector(Connector connector) {
        connectors.add(connector);
    }

    @Override
    public Engine getEngine() {
        return engine;
    }

}
