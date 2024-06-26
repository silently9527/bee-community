package org.herman.bee.common.pipeline;

public class StandardPipeline implements Pipeline {
    @Override
    public void addInterceptor(Interceptor interceptor) {

    }

    @Override
    public boolean applyPreHandle() {
        return false;
    }

    @Override
    public void applyPostHandle() {

    }
}
