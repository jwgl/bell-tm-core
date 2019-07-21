package cn.edu.bnuz.bell.tm.zuul

import cn.edu.bnuz.bell.config.ExternalConfigLoader
import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.EnvironmentAware
import org.springframework.core.env.Environment

@SpringBootApplication
class Application extends GrailsAutoConfiguration implements EnvironmentAware{

    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

    @Override
    void setEnvironment(Environment environment) {
        ExternalConfigLoader.load(environment)
    }
}