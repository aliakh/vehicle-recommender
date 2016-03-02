package demo.domain.source.style;

import demo.domain.target.AttributableEntity;

public class Engine extends AttributableEntity {

    private static final String[] NUMERIC = {
            "horsepower",
            "torque",
            "displacement",
    };
    
    private static final String[] NOMINAL_OPTIONAL = {
            "name",
            "compressorType",
            "compressionRatio",
            "cylinder",
            "size",
            "configuration",
            "fuelType",
            "totalValves",
            "type",
            "code",
            "manufacturerEngineCode",
    };

    private static final String[] NOMINAL_MANDATORY = {
            "cylinder",
            "type",
    };

    private String id;
    private String name;
    private String equipmentType;
    private String compressorType;
    private Integer compressionRatio;
    private Integer cylinder;
    private Integer size;
    private Integer displacement;
    private String configuration;
    private String fuelType;
    private Integer horsepower;
    private Integer torque;
    private Integer totalValves;
    private String type;
    private String code;
    private String manufacturerEngineCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getCompressorType() {
        return compressorType;
    }

    public void setCompressorType(String compressorType) {
        this.compressorType = compressorType;
    }

    public Integer getCompressionRatio() {
        return compressionRatio;
    }

    public void setCompressionRatio(Integer compressionRatio) {
        this.compressionRatio = compressionRatio;
    }

    public Integer getCylinder() {
        return cylinder;
    }

    public void setCylinder(Integer cylinder) {
        this.cylinder = cylinder;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getDisplacement() {
        return displacement;
    }

    public void setDisplacement(Integer displacement) {
        this.displacement = displacement;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Integer getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }

    public Integer getTorque() {
        return torque;
    }

    public void setTorque(Integer torque) {
        this.torque = torque;
    }

    public Integer getTotalValves() {
        return totalValves;
    }

    public void setTotalValves(Integer totalValves) {
        this.totalValves = totalValves;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getManufacturerEngineCode() {
        return manufacturerEngineCode;
    }

    public void setManufacturerEngineCode(String manufacturerEngineCode) {
        this.manufacturerEngineCode = manufacturerEngineCode;
    }

    @Override
    public String[] getNumericAbsoluteNames() {
        return NUMERIC;
    }

    @Override
    public String[] getNominalOptionalNames() {
        return NOMINAL_OPTIONAL;
    }

    @Override
    public String[] getNominalMandatoryNames() {
        return NOMINAL_MANDATORY;
    }
}
