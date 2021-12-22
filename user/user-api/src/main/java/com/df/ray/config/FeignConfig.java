package com.df.ray.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.MDC;

/**
 * FeignConfig
 *
 * @author ray
 * @date 2021/12/20
 */
public class FeignConfig implements RequestInterceptor {
    private static final String  TRACE_ID = "trace_id";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String traceId = MDC.get(TRACE_ID);
        requestTemplate.header(TRACE_ID, traceId);
    }
}
