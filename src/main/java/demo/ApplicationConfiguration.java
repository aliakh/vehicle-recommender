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


@Configuration
public class ApplicationConfiguration {

	@Value("${service.name:unknown}")
	private String serviceName;

	@Value("${service.trimString:unknown}")
	private String trimString;

	@Autowired
	MetricRegistry metricsRegistry;

	@Bean
	public PublicMetrics publicMetrics() {
		MetricNamer metricNamer = new MetricNamer(serviceName, trimString);
		return new CodahaleMetricsAdapter(metricNamer, metricsRegistry);
	}
}