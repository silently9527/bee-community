package org.herman.bee.server;

import org.herman.bee.common.Lifecycle;
import org.herman.bee.server.connector.request.Request;
import org.herman.bee.server.connector.response.Response;
import org.herman.bee.server.engine.handler.Handler;
import org.herman.bee.server.engine.mapping.MappingInfo;


public interface Engine extends Lifecycle {

    void invoke(Request request, Response response);

    void register(MappingInfo mappingInfo, Handler handler);

}
