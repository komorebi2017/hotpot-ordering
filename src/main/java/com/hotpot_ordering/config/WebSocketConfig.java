package com.hotpot_ordering.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.servlet.ServletContext;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/8 21:08
 * @ Description:
 */
@Component
public class WebSocketConfig {

   /* @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
*/
    @Bean
    public ServletContextAware endpointExporterInitializer(final ApplicationContext applicationContext) {
        return new ServletContextAware() {
            @Override
            public void setServletContext(ServletContext servletContext) {
                ServerEndpointExporter exporter = new ServerEndpointExporter();
                exporter.setApplicationContext(applicationContext);
                exporter.afterPropertiesSet();
            }
        };
    }

}
