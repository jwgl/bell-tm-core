import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper

// Place your Spring DSL code here
beans = {
    serviceRouteMapper(PatternServiceRouteMapper, 'tm-(?<name>.*)-(?<type>.*)', '${type}/${name}')
}
