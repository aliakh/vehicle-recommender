package demo.domain.source.style;

import demo.domain.target.AttributableEntity;

public class MilesPerGallon extends AttributableEntity {

    public static String[] NUMERIC = {
            "highway",
            "city",
    };

    private Integer highway;
    private Integer city;

    public Integer getHighway() {
        return highway;
    }

    public void setHighway(Integer highway) {
        this.highway = highway;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    @Override
    public String[] getNumericAbsoluteNames() {
        return NUMERIC;
    }
}
