package cn.edu.bnuz.bell.tm.zuul

import cn.edu.bnuz.bell.config.ExternalConfigLoader
import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.zuul.EnableZuulProxy
import org.springframework.context.EnvironmentAware
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.web.context.request.RequestContextListener

@SpringBootApplication
@EnableZuulProxy
@EnableGlobalMethodSecurity(securedEnabled = true)
class Application extends GrailsAutoConfiguration implements EnvironmentAware{

    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

    @Bean
    RequestContextListener requestContextListener(){
        new RequestContextListener();
    }

    @Override
    void setEnvironment(Environment environment) {
        ExternalConfigLoader.load(environment)
    }
}