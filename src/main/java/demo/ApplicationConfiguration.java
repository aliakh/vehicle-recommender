package demo;


public class ApplicationConfiguration {


/*
	@Bean
	public LoadBalancer loadBalancer() {
		LoadBalancingStrategy strategy = new RoundRobinLoadBalancingStrategy();

		DnsSrvResolver resolver = DnsSrvResolvers.newBuilder()
				.cachingLookups(true)
				.retainingDataOnFailures(true)
				.metered(new CodahaleSpringBootReporter(metricsRegistry))
				.dnsLookupTimeoutMillis(1000)
				.build();

		return new LoadBalancer(strategy, resolver);
	}
*/
}