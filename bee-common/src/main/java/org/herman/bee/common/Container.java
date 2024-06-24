package org.herman.bee.common;

import org.herman.bee.common.pipeline.Pipeline;

public interface Container extends Lifecycle {

    Pipeline getPipeline();

}
