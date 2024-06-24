package org.herman.bee.common;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;


@Slf4j
public abstract class LifecycleBase implements Lifecycle {
    protected final AtomicBoolean started = new AtomicBoolean(false);


    @Override
    public void start() {
        if (started.compareAndSet(false, true)) {
            try {
                startInternal();
            } catch (Throwable e) {
                log.info("start error", e);
            }
        }
    }


    @Override
    public void stop() {
        if (started.compareAndSet(true, false)) {
            stopInternal();
        }
    }

    protected abstract void startInternal();

    protected abstract void stopInternal();
}
