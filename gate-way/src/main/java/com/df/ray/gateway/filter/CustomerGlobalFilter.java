package com.df.ray.gateway.filter;

import com.df.ray.model.UserInfo;
import com.df.ray.userapi.IFeignUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * CustomerGlobalFilter
 *
 * @author ray
 * @date 2021/12/24
 */
@Component
public class CustomerGlobalFilter implements GlobalFilter, Ordered {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerGlobalFilter.class);

    @Autowired
    private IFeignUserService iFeignUserService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取到请求参数
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        LOG.info("****************进入自定义全局过滤器:request-uri-{}****************", request.getURI());
        // 从请求头中获取到token
//        if (request.getHeaders().containsKey(securityProperties.getTokenKey())){
//            String token  = request.getHeaders().get(securityProperties.getTokenKey()).get(0);
//            String path = request.getURI().getPath();
//            securityClientService.hasUrl(token,path);
//        }
        UserInfo userInfo = iFeignUserService.getUserInfo();
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
