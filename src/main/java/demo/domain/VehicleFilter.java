package demo.domain;

import com.google.common.collect.Lists;

import java.util.List;

public class VehicleFilter {

    private final List<String> filteredMakes;
    private final List<String> filteredYears;

    public VehicleFilter(String makes, String years) {
        this.filteredMakes = (makes == null) ? Lists.newArrayList() : Lists.newArrayList(makes.split(","));
        this.filteredYears = (years == null) ? Lists.newArrayList() : Lists.newArrayList(years.split(","));
    }

    public boolean skipMake(String make) {
        return !this.filteredMakes.contains(make);
    }

    public boolean skipYear(String year) {
        return !this.filteredYears.contains(year);
    }
}
