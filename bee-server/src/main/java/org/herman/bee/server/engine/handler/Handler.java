package org.herman.bee.server.engine.handler;

import org.herman.bee.common.pipeline.Pipeline;
import org.herman.bee.server.connector.request.Request;
import org.herman.bee.server.connector.response.Response;

public interface Handler {

    void handle(Request request, Response response);

    Pipeline getPipeline();

}
