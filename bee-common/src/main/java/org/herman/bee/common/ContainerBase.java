package org.herman.bee.common;

import org.herman.bee.common.pipeline.Pipeline;
import org.herman.bee.common.pipeline.StandardPipeline;

public abstract class ContainerBase extends LifecycleBase implements Container {
    private final Pipeline pipeline = new StandardPipeline();

    @Override
    public Pipeline getPipeline() {
        return pipeline;
    }


}
