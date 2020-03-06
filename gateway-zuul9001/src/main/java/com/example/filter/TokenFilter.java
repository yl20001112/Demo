package com.example.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class TokenFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;        //过滤器类型;
    }

    @Override
    public int filterOrder() {
        return 1;                               //优先级位1，数字越大，优先级越低；
    }

    @Override
    public boolean shouldFilter() {
        System.out.println("TokenFilter shouldFilter");
        return true;                            //是否执行该过滤器，true:需要过滤；
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("run");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token = request.getHeader("token");
        if(token==null || token.isEmpty()){
            ctx.setResponseStatusCode(405);
            ctx.getResponse().setContentType("text/html;charset=utf-8");
            ctx.setResponseBody("{\"msg\":\"没有权限访问！\"}");
            ctx.setSendZuulResponse(false);     //过滤该请求，不对其进行路由
        }else{
            ctx.setResponseStatusCode(200);
            ctx.setSendZuulResponse(true);      //对该请求进行路由
        }

        return null;
    }
}
