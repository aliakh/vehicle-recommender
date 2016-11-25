package demo;

import com.codahale.metrics.MetricRegistry;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import demo.metrics.CodahaleMetricsAdapter;
import demo.metrics.MetricNamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


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