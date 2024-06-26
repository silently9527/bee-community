package org.herman.bee.server;

import org.herman.bee.common.Lifecycle;

import java.util.concurrent.ThreadPoolExecutor;

public interface Server extends Lifecycle {

    void addService(Service service);

    ThreadPoolExecutor getExecutor();

}
