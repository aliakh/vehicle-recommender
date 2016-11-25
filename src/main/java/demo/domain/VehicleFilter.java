package demo.domain;

import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import java.util.List;

public class VehicleFilter {

    private final List<String> filteredMakes;
    private final List<String> filteredYears;

    public VehicleFilter(String makes, String years) {
        this.filteredMakes = !StringUtils.hasText(makes) ? Lists.newArrayList() : Lists.newArrayList(makes.split(","));
        this.filteredYears = !StringUtils.hasText(years) ? Lists.newArrayList() : Lists.newArrayList(years.split(","));
    }

    public boolean skipMake(String make) {
        return !(this.filteredMakes.isEmpty() || this.filteredMakes.contains(make));
    }

    public boolean useMake(String make) {
        return this.filteredMakes.isEmpty() || this.filteredMakes.contains(make);
    }

    public boolean skipYear(String year) {
        return !(this.filteredYears.isEmpty() || this.filteredYears.contains(year));
    }

    public boolean useYear(String year) {
        return this.filteredYears.isEmpty() || this.filteredYears.contains(year);
    }
}
