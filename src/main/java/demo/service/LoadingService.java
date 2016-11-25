package demo.service;

import demo.domain.VehicleFilter;
import demo.domain.source.Make;
import demo.domain.source.Makes;
import demo.domain.source.Model;
import demo.domain.source.ModelYear;
import demo.domain.source.property.Properties;
import demo.domain.source.style.Style;
import demo.repository.mongodb.MakeRepository;
import demo.repository.mongodb.PropertyRepository;
import demo.repository.mongodb.StyleRepository;
import demo.repository.rest.AsyncRestRepository;
import demo.repository.rest.RestRepository;
import demo.sse.Event;
import demo.sse.Progress;
import demo.util.Delay;
import demo.util.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LoadingService implements HealthIndicator {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoadingService.class);

    @Autowired
    private RestRepository restRepository;

    @Autowired
    private AsyncRestRepository asyncRestRepository;

    @Autowired
    private StyleRepository styleRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private MakeRepository makeRepository;

    private final SseEmitter progressEmitter = new SseEmitter();

    private final SseEmitter errorEmitter = new SseEmitter();

    private List<Throwable> errors = new ArrayList<>();
    ;

    public SseEmitter getProgressEmitter() {
        return progressEmitter;
    }

    public SseEmitter getErrorEmitter() {
        return errorEmitter;
    }

    void sendProgress(Progress progress) {
        try {
            progressEmitter.send(new Event(progress), MediaType.APPLICATION_JSON);
        } catch (IOException e) {
            LOGGER.error("SSE sending error", e);
        }
    }

    void sendSseError(Throwable t) {
        try {
            errorEmitter.send(new Error(t), MediaType.APPLICATION_JSON);
        } catch (IOException e) {
            LOGGER.error("SSE sending error", e);
        }
    }

    private AtomicInteger counter1 = new AtomicInteger(0);
    private AtomicInteger counter2 = new AtomicInteger(0);

    private Progress progress;

    @Override
    public Health health() {
        return errors.isEmpty()
                ? Health.up().build()
                : Health.down().withDetail("Errors", errors).build();
    }

    public boolean load(VehicleFilter filter) {
        errors = new ArrayList<>();

        Timer timer = new Timer("Loading");
        progress = new Progress(getPercentMax(filter));

        makeRepository.deleteAll();
        styleRepository.deleteAll();
        propertyRepository.deleteAll();

        Makes makes = processMakes(filter);
        makeRepository.save(makes.getMakes());

        while ((counter1.get() > 0) && (counter2.get() > 0)) {
            LOGGER.info("Finishing: " + counter1.get() + " / " + counter2.get() + " / " + errors.size());
            Delay.sleep(1000);
        }

        timer.stop();

        LOGGER.info("Errors count: " + errors.size());
        return errors.isEmpty();
    }

    private int getPercentMax(VehicleFilter filter) {
        int max = 0;

        Makes makes = restRepository.loadMakes();
        for (Make make : makes.getMakes()) {
            if (filter.skipMake(make.getName())) {
                continue;
            }

            for (Model model : make.getModels()) {
                max += model.getYears().size();
            }
        }

        return max;
    }

    private Makes processMakes(VehicleFilter filter) {
        Makes makes = restRepository.loadMakes();

        for (int i = 0; i < makes.getMakes().size(); i++) {
            Make make = makes.getMakes().get(i);
            LOGGER.info("Read make: " + make.getName());

            if (filter.skipMake(make.getName())) {
                LOGGER.info("Make skipped");
                continue;
            }

            processModels(filter, make);
        }

        return makes;
    }

    private void processModels(VehicleFilter filter, Make make) {
        for (int i = 0; i < make.getModels().size(); i++) {
            Model model = make.getModels().get(i);
            LOGGER.info("Read model: " + model.getName());

            model = restRepository.loadModel(make.getNiceName(), model.getNiceName());
            make.getModels().set(i, model);

            processModelYears(filter, model);
        }
    }

    private void processModelYears(VehicleFilter filter, Model model) {
        for (int i = 0; i < model.getYears().size(); i++) {
            progress = progress.next();
            sendProgress(progress);

            ModelYear modelYear = model.getYears().get(i);
            LOGGER.info("Read year: " + modelYear.getYear());

            if (filter.skipYear(modelYear.getYear())) {
                LOGGER.info("Year skipped");
                continue;
            }

            processStyles(modelYear);
        }
    }

    private void processStyles(final ModelYear modelYear) {
        if (modelYear.getStyles() != null) {
            for (int i = 0; i < modelYear.getStyles().size(); i++) {
                LOGGER.info("Waiting: " + counter1.get() + " / " + counter2.get());

                Style style = modelYear.getStyles().get(i);
                LOGGER.info("Read style: " + style.getId());

                processStyle(style.getId());
            }
        }
    }

    private void processStyle(final String styleId) {
        ListenableFuture<ResponseEntity<Style>> future1 = asyncRestRepository.getStyle(styleId);
        counter1.getAndIncrement();
        future1.addCallback(new ListenableFutureCallback<ResponseEntity<Style>>() {

            @Override
            public void onSuccess(ResponseEntity<Style> response) {
                counter1.getAndDecrement();

                Style style = response.getBody();

                styleRepository.save(style);
                LOGGER.debug("Write style: " + style.getId());
            }

            @Override
            public void onFailure(Throwable t) {
                counter1.getAndDecrement();

                LOGGER.error("Error: " + t);

                errors.add(t);
                sendSseError(t);
            }
        });

        ListenableFuture<ResponseEntity<Properties>> future2 = asyncRestRepository.getStyleHolder(styleId);
        counter2.getAndIncrement();
        future2.addCallback(new ListenableFutureCallback<ResponseEntity<Properties>>() {

            @Override
            public void onSuccess(ResponseEntity<Properties> response) {
                counter2.getAndDecrement();

                Properties properties = response.getBody();
                properties.normalize();

                propertyRepository.save(properties.getProperties());
                LOGGER.debug("Write properties: " + properties.getIds());
            }

            @Override
            public void onFailure(Throwable t) {
                counter2.getAndDecrement();

                LOGGER.error("Error: " + t);

                errors.add(t);
                sendSseError(t);
            }
        });
    }
}

// TODO progressEmitter.complete(); progressEmitter.completeWithError(e);