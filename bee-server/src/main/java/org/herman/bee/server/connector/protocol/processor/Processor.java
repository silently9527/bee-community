package org.herman.bee.server.connector.protocol.processor;

import org.herman.bee.server.Engine;
import org.herman.bee.server.connector.request.Request;
import org.herman.bee.server.connector.response.Response;

public interface Processor {

    void process(Request request, Response response);

    Engine getEngine();

}
