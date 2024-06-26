package org.herman.bee.server.engine;

import lombok.extern.slf4j.Slf4j;
import org.herman.bee.common.LifecycleBase;
import org.herman.bee.server.Engine;
import org.herman.bee.server.connector.request.Request;
import org.herman.bee.server.connector.response.Response;
import org.herman.bee.server.engine.handler.Handler;
import org.herman.bee.server.engine.mapping.HandlerMapping;
import org.herman.bee.server.engine.mapping.MappingInfo;
import org.herman.bee.server.engine.mapping.StandardHandlerMapping;

import java.util.Objects;

@Slf4j
public class StandardEngine extends LifecycleBase implements Engine {
    private final HandlerMapping handlerMapping = new StandardHandlerMapping();

    @Override
    public void invoke(Request request, Response response) {
        Handler handler = handlerMapping.getHandler(request);
        if (Objects.isNull(handler)) {
            log.error("can not find handler for url:{}", request.getURL());
            return;
        }
        handler.handle(request, response);
    }

    @Override
    public void register(MappingInfo mappingInfo, Handler handler) {
        handlerMapping.register(mappingInfo, handler);
    }

    @Override
    protected void startInternal() {
    }

    @Override
    protected void stopInternal() {
    }

}
