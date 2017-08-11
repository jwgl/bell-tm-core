import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper
import cn.edu.bnuz.bell.grails.EmptyMappingContextFactoryBean

beans = {
    serviceRouteMapper(PatternServiceRouteMapper, 'tm-(?<name>.*)-(?<type>.*)', '${type}/${name}')
    grailsDomainClassMappingContext(EmptyMappingContextFactoryBean)
}
