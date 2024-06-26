package org.herman.bee.server;

import org.herman.bee.common.LifecycleBase;

import java.util.concurrent.ThreadPoolExecutor;

public class StandardServer extends LifecycleBase implements Server {


    @Override
    public void addService(Service service) {

    }

    @Override
    public ThreadPoolExecutor getExecutor() {
        return null;
    }


    @Override
    protected void startInternal() {

    }

    @Override
    protected void stopInternal() {

    }
}
