package org.herman.bee.common.pipeline;

public interface Pipeline {

    void addInterceptor(Interceptor interceptor);

    boolean applyPreHandle();

    void applyPostHandle();


}
