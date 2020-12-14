package com.jieai.config;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
public class RequestMappingAppListener implements ApplicationListener<ContextRefreshedEvent>{
 private static final Logger logger=LoggerFactory.getLogger(RequestMappingAppListener.class);

 
 public void onApplicationEvent(ContextRefreshedEvent event){
    if(event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")){
       return;
    }
   
    ApplicationContext ctx=event.getApplicationContext();
    RequestMappingHandlerMapping rmhm=ctx.getBean(RequestMappingHandlerMapping.class);
    if(rmhm==null) return;

    Map<RequestMappingInfo,HandlerMethod> map=rmhm.getHandlerMethods();
    for(Entry<RequestMappingInfo,HandlerMethod> e:map.entrySet()){
      logger.error(e.getKey().toString()+","+e.getValue().toString());
    	System.out.println(e.getKey().toString()+","+e.getValue().toString());
     }
  
 }
}