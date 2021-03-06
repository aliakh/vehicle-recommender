package demo.service;

import demo.repository.mongodb.MakeRepository;
import demo.repository.mongodb.PropertyRepository;
import demo.repository.mongodb.StyleRepository;
import demo.util.Process;
import demo.util.Timer;
import demo.domain.VehicleFilter;
import demo.domain.source.Make;
import demo.domain.source.Makes;
import demo.domain.source.Model;
import demo.domain.source.ModelYear;
import demo.domain.source.property.Properties;
import demo.domain.source.style.Style;
import demo.repository.rest.AsyncRestRepository;
import demo.repository.rest.RestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public /*TODO*/ class LoadingService {

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

    private AtomicInteger counter1 = new AtomicInteger(0);
    private AtomicInteger counter2 = new AtomicInteger(0);

    private Process process;

    public String load(VehicleFilter filter) {
        Timer timer = new Timer("Loading");
        process = new Process(getPercentMax(filter));

        makeRepository.deleteAll();
        styleRepository.deleteAll();
        propertyRepository.deleteAll();

        Makes makes = processMakes(filter);
        makeRepository.save(makes.getMakes());

        while ((counter1.get() > 0) && (counter2.get() > 0)) {
            System.out.println("Finishing: " + counter1.get() + " / " + counter1.get());
            sleep(1000);
        }

        timer.stop();

        return "OK";
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
            System.out.println("Read make: " + make.getName());

            if (filter.skipMake(make.getName())) {
                System.out.println("Make skipped");
                continue;
            }

            processModels(filter, make);
        }

        return makes;
    }

    private void processModels(VehicleFilter filter, Make make) {
        for (int i = 0; i < make.getModels().size(); i++) {
            Model model = make.getModels().get(i);
            System.out.println("Read model: " + model.getName());

            model = restRepository.loadModel(make.getNiceName(), model.getNiceName());
            make.getModels().set(i, model);

            processModelYears(filter, model);
        }
    }

    private void processModelYears(VehicleFilter filter, Model model) {
        for (int i = 0; i < model.getYears().size(); i++) {
            process.inc();

            ModelYear modelYear = model.getYears().get(i);
            System.out.println("Read year: " + modelYear.getYear());

            if (filter.skipYear(modelYear.getYear())) {
                System.out.println("Year skipped");
                continue;
            }

            processStyles(modelYear);
        }
    }

    private void processStyles(final ModelYear modelYear) {
        for (int i = 0; i < modelYear.getStyles().size(); i++) {
            System.out.println(process.get());
            System.out.println("Waiting: " + counter1.get() + " / " + counter1.get());

            Style style = modelYear.getStyles().get(i);
            System.out.println("Read style: " + style.getId());

            processStyle(style.getId());
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
                System.out.println("Write style: " + style.getId());
            }

            @Override
            public void onFailure(Throwable t) {
                counter1.getAndDecrement();

                System.out.println("Error: " + t);
                throw new RuntimeException(t);
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
                System.out.println("Write properties: " + properties.getIds());
            }

            @Override
            public void onFailure(Throwable t) {
                counter2.getAndDecrement();

                System.out.println("Error: " + t);
                throw new RuntimeException(t);
            }
        });
    }

    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
