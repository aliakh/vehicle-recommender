package demo.domain.source.style;

import demo.domain.target.AttributableEntity;

public class Transmission extends AttributableEntity {

    private static final String[] NOMINAL_OPTIONAL = {
            "name",
            "automaticType",
            "transmissionType",
            "numberOfSpeeds"
    };

    private String id;
    private String name;
    private String equipmentType;
    private String automaticType;
    private String transmissionType;
    private String numberOfSpeeds;

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

    public String getAutomaticType() {
        return automaticType;
    }

    public void setAutomaticType(String automaticType) {
        this.automaticType = automaticType;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getNumberOfSpeeds() {
        return numberOfSpeeds;
    }

    public void setNumberOfSpeeds(String numberOfSpeeds) {
        this.numberOfSpeeds = numberOfSpeeds;
    }

    @Override
    public String[] getNominalOptionalNames() {
        return NOMINAL_OPTIONAL;
    }
}
