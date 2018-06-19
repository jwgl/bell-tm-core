import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper

beans = {
    serviceRouteMapper(PatternServiceRouteMapper, 'tm-(?<name>.*)-(?<type>.*)', '${type}/${name}')
}
