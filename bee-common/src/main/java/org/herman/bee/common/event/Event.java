package org.herman.bee.common.event;

import lombok.Getter;

@Getter
public abstract class Event {
    private String eventType;

    public Event(String eventType) {
        this.eventType = eventType;
    }
}
