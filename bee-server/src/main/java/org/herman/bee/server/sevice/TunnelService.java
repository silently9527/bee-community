package org.herman.bee.server.sevice;

import org.herman.bee.common.ContainerBase;
import org.herman.bee.common.event.EventListener;
import org.herman.bee.common.event.EventMgt;
import org.herman.bee.server.Engine;
import org.herman.bee.server.Service;
import org.herman.bee.server.connector.Connector;
import org.herman.bee.server.event.AddTunnelEvent;
import org.herman.bee.server.event.EventType;

public class TunnelService extends ContainerBase implements Service, EventListener<AddTunnelEvent> {

    public TunnelService() {

        EventMgt.register(EventType.AddTunnel.name(), this);
    }

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

    @Override
    public void onEvent(AddTunnelEvent event) {

    }
}
