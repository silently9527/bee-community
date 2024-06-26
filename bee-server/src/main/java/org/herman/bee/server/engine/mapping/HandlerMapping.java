package org.herman.bee.server.engine.mapping;

import org.herman.bee.server.connector.request.Request;
import org.herman.bee.server.engine.handler.Handler;

public interface HandlerMapping {

    Handler getHandler(Request request);

    void register(MappingInfo mappingInfo, Handler handler);

}
