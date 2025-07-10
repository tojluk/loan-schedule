package com.timvero.loanschedule.infra.config;


import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.UUID;

@Component
public class MdcFilterConfig implements WebFilter
        // for WebMvc extends OncePerRequestFilter
{

    private static final String REQUEST_ID = "request-id";
    private static final String REQUEST_ID_HEADER_NAME = "X-Request-id";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String requestId = exchange.getRequest()
                                   .getHeaders()
                                   .getFirst(REQUEST_ID_HEADER_NAME);

        if (requestId == null || requestId.isEmpty()) {
            requestId = UUID.randomUUID().toString();
        }

        exchange.getResponse().getHeaders()
                .add(REQUEST_ID_HEADER_NAME, requestId);

        return chain.filter(exchange)
                    .contextWrite(Context.of(REQUEST_ID, requestId));
    }

    /* WebMVC
     @Override
     protected void doFilterInternal(HttpServletRequest request,
     HttpServletResponse response,
     FilterChain filterChain) throws ServletException, IOException {
     String requestId = UUID.randomUUID().toString();
     MDC.put(REQUEST_ID, requestId);
     response.setHeader(REQUEST_ID_HEADER_NAME, requestId);
     try {
     filterChain.doFilter(request, response);
     } finally {
     MDC.remove(REQUEST_ID);
     }
     }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getRequestURI().startsWith("/api/v1/");
    }
     */
}

