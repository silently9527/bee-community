package org.herman.bee.server.engine.mapping;

import org.herman.bee.server.connector.request.Request;
import org.herman.bee.server.engine.handler.Handler;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class StandardHandlerMapping implements HandlerMapping {

    private final Map<String, Handler> handlerMap = new ConcurrentHashMap<>();

    @Override
    public Handler getHandler(Request request) {
        return handlerMap.get(request.getURL());
    }

    @Override
    public void register(MappingInfo mappingInfo, Handler handler) {
        Handler existedHandler = handlerMap.get(mappingInfo.toString());
        if (Objects.nonNull(existedHandler)) {
            throw new IllegalArgumentException("mappingInfo handler existed: " + mappingInfo);
        }
        handlerMap.put(mappingInfo.toString(), handler);
    }


}
