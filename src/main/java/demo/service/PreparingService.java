package demo.service;

import com.google.common.collect.Lists;
import demo.domain.VehicleFilter;
import demo.domain.source.Make;
import demo.domain.source.Makes;
import demo.domain.source.Model;
import demo.domain.source.ModelYear;
import demo.domain.source.property.Property;
import demo.domain.source.style.Style;
import demo.domain.target.Vehicle;
import demo.domain.target.VehicleBuilder;
import demo.repository.*;
import demo.repository.mongodb.MakeRepository;
import demo.repository.mongodb.PropertyRepository;
import demo.repository.mongodb.StyleRepository;
import demo.repository.rest.RestRepository;
import demo.util.Process;
import demo.util.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public /*TODO*/ class PreparingService {

    @Autowired
    private RestRepository restRepository;

    @Autowired
    private MakeRepository makeRepository;

    @Autowired
    private StyleRepository styleRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    private Process process;

    private int total, error;

    public Object process(VehicleFilter filter) {
        Timer timer = new Timer("Deleting");
        vehicleRepository.deleteAll();
        timer.stop();

        process = new Process(getPercentMax(filter));

        timer = new Timer("Processing");
        List<Vehicle> vehicles = getVehicles(filter);
        timer.stop();

        int sec = timer.stop();
        System.out.println("total " + total + " error " + error);
        System.out.println("performance: " + total / sec);

        timer = new Timer("Saving");
        vehicleRepository.save(vehicles);
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

    private List<Vehicle> getVehicles(VehicleFilter filter) {
        List<Make> makes = makeRepository.findAll();
        List<Vehicle> vehicles = Lists.newArrayList();

        for (Make make : makes) {
            if (filter.skipMake(make.getName())) {
                System.out.println("Make skipped");
                continue;
            }

            for (Model model : make.getModels()) {
                for (ModelYear modelYear : model.getYears()) {
                    process.inc();
                    System.out.println(process.get());

                    if (filter.skipYear(modelYear.getYear())) {
                        System.out.println("Year skipped");
                        continue;
                    }

                    for (Style style : modelYear.getStyles()) {
                        String styleId = style.getId();

                        style = styleRepository.findOne(styleId);
                        Property property = propertyRepository.findOne(styleId);

                        Vehicle vehicle = VehicleBuilder.create(style, property);
                        if (!vehicle.isValid()) {
                            error++;
                            continue;
                        }

                        total++;
                        vehicles.add(vehicle);
                    }
                }
            }
        }

        return vehicles;
    }
}
