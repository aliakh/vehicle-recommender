package demo.domain;

import com.google.common.collect.Lists;

import java.util.List;

public /*TODO*/ class VehicleFilter {

    private List<String> filteredMakes;
    private List<String> filteredYears;

    public VehicleFilter(String makes, String years) {
        if (makes != null) {
            filteredMakes = Lists.<String>newArrayList(makes.split(","));
        }
        if (years != null) {
            filteredYears = Lists.<String>newArrayList(years.split(","));
        }
    }

    public boolean skipMake(String make) {
        return ((filteredMakes != null) && (!filteredMakes.contains(make)));
    }

    public boolean skipYear(String year) {
        return ((filteredYears != null) && (!filteredYears.contains(year)));
    }
}
