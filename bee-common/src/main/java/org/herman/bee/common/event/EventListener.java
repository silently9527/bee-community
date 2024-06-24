package org.herman.bee.common.event;

public interface EventListener<T extends Event> {

    void onEvent(T event);

}
