package demo.domain.source.style;

import demo.domain.target.AttributableEntity;

public class Submodel extends AttributableEntity {

    public static String[] NOMINAL_MANDATORY = {
            "body",
    };

    public static String[] NOMINAL_OPTIONAL = {
            "tuner",
            "modelName",
            "fuel",
    };

    private String body;
    private String tuner;
    private String modelName;
    private String niceName;
    private String fuel;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTuner() {
        return tuner;
    }

    public void setTuner(String tuner) {
        this.tuner = tuner;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getNiceName() {
        return niceName;
    }

    public void setNiceName(String niceName) {
        this.niceName = niceName;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    @Override
    public String[] getNominalMandatoryNames() {
        return NOMINAL_MANDATORY;
    }

    @Override
    public String[] getNominalOptionalNames() {
        return NOMINAL_OPTIONAL;
    }
}
