package demo.domain.source.style;

import com.fasterxml.jackson.annotation.JsonProperty;
import demo.domain.target.AttributableEntity;

public class Categories extends AttributableEntity {

    private static final String[] NOMINAL_OPTIONAL = {
            "market",
            "epaClass",
            "vehicleSize",
            "crossover",
            "primaryBodyType",
            "vehicleStyle",
            "vehicleType",
            "manufacturerCabType",
    };

    private String market;
    @JsonProperty("EPAClass")
    private String epaClass;
    private String vehicleSize;
    private String crossover;
    private String primaryBodyType;
    private String vehicleStyle;
    private String vehicleType;
    private String manufacturerCabType;

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getEpaClass() {
        return epaClass;
    }

    public void setEpaClass(String epaClass) {
        this.epaClass = epaClass;
    }

    public String getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(String vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public String getCrossover() {
        return crossover;
    }

    public void setCrossover(String crossover) {
        this.crossover = crossover;
    }

    public String getPrimaryBodyType() {
        return primaryBodyType;
    }

    public void setPrimaryBodyType(String primaryBodyType) {
        this.primaryBodyType = primaryBodyType;
    }

    public String getVehicleStyle() {
        return vehicleStyle;
    }

    public void setVehicleStyle(String vehicleStyle) {
        this.vehicleStyle = vehicleStyle;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getManufacturerCabType() {
        return manufacturerCabType;
    }

    public void setManufacturerCabType(String manufacturerCabType) {
        this.manufacturerCabType = manufacturerCabType;
    }

    @Override
    public String[] getNominalOptionalNames() {
        return NOMINAL_OPTIONAL;
    }
}
