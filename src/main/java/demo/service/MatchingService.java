package demo.service;

import com.google.common.collect.Lists;
import demo.domain.target.IntegerAttributeGroups;
import demo.domain.target.Vehicle;
import demo.repository.mongodb.VehicleRepository;
import demo.util.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public /*TODO*/ class MatchingService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleService vehicleService;

    public Object match(String styleId) {
        Vehicle vehicle = vehicleRepository.findOne(styleId);
        Assert.notNull(vehicle);

        Timer timer = new Timer("Loading");
        List<Vehicle> vehicles = vehicleRepository.findByYear(vehicle.getYear());
        timer.stop();

        timer = new Timer("Filtering");
        vehicles = vehicleService.filterVehicles(vehicles, vehicle);
        timer.stop();

        List<Vehicle> vehicles2= Lists.newArrayList(vehicles);
        vehicles2.add(vehicle);
        IntegerAttributeGroups numericMaximal = vehicleService.getNumericMaximal(vehicles2);

        timer = new Timer("Normalizing");
        vehicleService.normalizeAttribute(numericMaximal, vehicles);
        vehicleService.normalizeAttribute(numericMaximal, vehicle);
        timer.stop();

        timer = new Timer("Scoring");
        vehicleService.setScore(vehicles, vehicle);
        timer.stop();

        timer = new Timer("Sorting");
        vehicleService.sortByScore(vehicles);
        timer.stop();

        return vehicles;
    }
}
