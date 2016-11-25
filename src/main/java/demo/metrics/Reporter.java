package demo.metrics;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

public class Reporter /*implements DnsReporter*/ {

    private MetricRegistry metrics;
    private Timer lookups;
    private Counter failures;
    private Counter empties;

    public Reporter(MetricRegistry metrics) {
        this.metrics = metrics;
        lookups = metrics.timer(MetricRegistry.name(Reporter.class, "srvlookup"));
        failures = metrics.counter(MetricRegistry.name(Reporter.class, "srvlookupfailures"));
        empties = metrics.counter(MetricRegistry.name(Reporter.class, "srvlookupempty"));
    }
/*
    @Override
    public DnsTimingContext resolveTimer() {
        return new DnsTimingContext() {
            final Timer.Context context = lookups.time();

            @Override
            public void stop() {
                context.stop();
            }
        };
    }

    @Override
    public void reportFailure(Throwable error) {
        failures.inc();
        error.printStackTrace(System.err);
    }

    @Override
    public void reportEmpty() {
        empties.inc();
    }
*/
}
