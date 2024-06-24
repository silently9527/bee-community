package org.herman.bee.common.event;

import cn.hutool.core.collection.CollectionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventMgt {
    private static final Map<String, List<EventListener<?>>> eventTypeListMap = new ConcurrentHashMap<>();


    public static void register(String eventType, EventListener<?> listener) {
        List<EventListener<?>> eventListeners = eventTypeListMap.get(eventType);
        if (CollectionUtil.isEmpty(eventListeners)) {
            eventListeners = new ArrayList<>();
            eventTypeListMap.put(eventType, eventListeners);
        }

        eventListeners.add(listener);
    }


    @SuppressWarnings("unchecked")
    public static <T extends Event> void publish(T event) {
        List<EventListener<?>> eventListeners = eventTypeListMap.get(event.getEventType());
        eventListeners.forEach(eventListener -> ((EventListener<T>) eventListener).onEvent(event));
    }
}
