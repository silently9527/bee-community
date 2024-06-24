package org.herman.bee.server;

import org.herman.bee.common.Container;

public interface Server extends Container {

    void addService(Service service);

}
