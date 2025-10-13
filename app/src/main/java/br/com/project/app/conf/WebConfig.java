package br.com.project.app.conf;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig  implements WebMvcConfigurer{
    @Override 
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers){
       PageableHandlerMethodArgumentResolver pg = new PageableHandlerMethodArgumentResolver(); 
       pg.setFallbackPageable(PageRequest.of(0, 5));
       resolvers.add(pg);
    }

    
}