package demo.service;

import com.google.common.collect.Lists;
import demo.domain.target.IntegerAttributeGroups;
import demo.domain.target.Numeric;
import demo.domain.target.Vehicle;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public /*TODO*/ class VehicleService {

    public IntegerAttributeGroups getNumericMaximal(List<Vehicle> vehicles) {
        IntegerAttributeGroups numericMaximal = new IntegerAttributeGroups();

        for (Vehicle vehicle : vehicles) {
            for (String groupName : vehicle.getNumeric().getGroupNames()) {
                Map<String, Numeric> group = vehicle.getNumeric().getGroup(groupName);
                if (group != null) {
                    for (String attributeName : group.keySet()) {
                        Integer valueCurrent = group.get(attributeName).getAbsolute();
                        if (valueCurrent != null) {
                            Integer valueMaximal = numericMaximal.getAttribute(groupName, attributeName);
                            if ((valueMaximal == null) || (valueCurrent > valueMaximal)) {
                                numericMaximal.setAttribute(groupName, attributeName, valueCurrent);
                            }
                        }
                    }
                }
            }
        }

        return numericMaximal;
    }

    public void normalizeAttribute(IntegerAttributeGroups numericMaximal, List<Vehicle> sources) {
        for (Vehicle source : sources) {
            normalizeAttribute(numericMaximal, source);
        }
    }

    public void normalizeAttribute(IntegerAttributeGroups numericMaximal, Vehicle source) {
        for (String groupName : source.getNumeric().getGroupNames()) {
            Map<String, Numeric> group = source.getNumeric().getGroup(groupName);
            if (group != null) {
                for (String attributeName : group.keySet()) {
                    Integer value = group.get(attributeName).getAbsolute();
                    if (value != null) {
                        Integer valueMaximal = numericMaximal.getAttribute(groupName, attributeName);
                        Assert.notNull(valueMaximal);
                        source.getNumeric().getAttribute(groupName, attributeName).setNormalized((double) value / valueMaximal);
                    }
                }
            }
        }
    }

    public List<Vehicle> filterVehicles(List<Vehicle> sources, Vehicle example) {
        List<Vehicle> target = Lists.newArrayList();

        for (Vehicle source : sources) {
            boolean flag = true;

            for (String groupName : example.getNominalMandatory().getGroupNames()) {
                Map<String, String> group = example.getNominalMandatory().getGroup(groupName);
                if (group != null) {
                    for (String attributeName : group.keySet()) {
                        String value1 = group.get(attributeName);
                        if (value1 != null) {
                            String value2 = source.getNominalMandatory().getAttribute(groupName, attributeName);
                            if ((value2 == null) || (!value1.equals(value2))) {
                                flag = false;
                            }
                        }
                    }
                }
            }

            if (flag) {
                target.add(source);
            }
        }

        return target;
    }

    public void setScore(List<Vehicle> sources, Vehicle vehicle) {
        for (Vehicle source : sources) {

            double totalDistance = 0;
            for (String groupName : vehicle.getNumeric().getGroupNames()) {
                Map<String, Numeric> group = vehicle.getNumeric().getGroup(groupName);
                if (group != null) {
                    for (String attributeName : group.keySet()) {
                        double distance = 0;

                        Double value1 = group.get(attributeName).getNormalized();
                        Double value2 = source.getNumeric().getAttribute(groupName, attributeName).getNormalized();
                        if ((value1 != null) && (value2 != null)) {
                            distance = value2 - value1;
                            source.getNumeric().getAttribute(groupName, attributeName).setPercent((int) (100 * (value2 - value1) / value1));
                        } else {
                            source.getNumeric().getAttribute(groupName, attributeName).setPercent(0);
                        }

                        totalDistance += Math.pow(distance, 2);
                    }
                }
            }
            source.setScore(1 / (1 + Math.sqrt(totalDistance)));
        }
    }

    public void sortByScore(List<Vehicle> source) {
        Collections.sort(source, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                if (o1.getScore() < o2.getScore()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }
}
