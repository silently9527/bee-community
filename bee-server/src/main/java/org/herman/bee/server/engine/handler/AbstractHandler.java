package org.herman.bee.server.engine.handler;

import lombok.extern.slf4j.Slf4j;
import org.herman.bee.common.pipeline.Pipeline;
import org.herman.bee.common.pipeline.StandardPipeline;
import org.herman.bee.server.connector.request.Request;
import org.herman.bee.server.connector.response.Response;

@Slf4j
public abstract class AbstractHandler implements Handler {
    private final Pipeline pipeline = new StandardPipeline();

    @Override
    public void handle(Request request, Response response) {
        if (pipeline.applyPreHandle()) {
            log.info("interceptor preHandle fail...");
            return;
        }
        doHandle(request, response);
        pipeline.applyPostHandle();
    }

    protected abstract void doHandle(Request request, Response response);

    @Override
    public Pipeline getPipeline() {
        return pipeline;
    }
}
