package src.superhakce.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Counter;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import java.util.concurrent.TimeUnit;

public class MetricsTest {

    public void run(String[] args) {
        MetricRegistry metricRegistry = new MetricRegistry();
        ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(metricRegistry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        new Thread(() -> {
            while (true){
                Counter counter = metricRegistry.counter("counter");
                counter.inc();
                Meter meter = metricRegistry.meter("meter");
                meter.mark();
                Histogram histogram = metricRegistry.histogram("histogram");
                histogram.update(1);
                Timer timer = metricRegistry.timer("timer");
                Timer.Context context = timer.time();
                try {
                    Thread.sleep(2000);
                }catch (Exception e){
                    System.err.println("some error happen");
                }finally {
                    context.stop();
                }
            }
        }).start();
        consoleReporter.start(1, TimeUnit.SECONDS);
    }

}
