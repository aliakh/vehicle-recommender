package demo.domain.target;

import com.google.common.collect.Maps;
import demo.domain.source.Entity;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.Map;

public /*TODO*/ class Vehicle extends Entity {

    @Transient
    private boolean valid = false;
    @Transient
    private double score;

    private String makeName;
    private String modelName;
    private String year;

    @Id
    private String styleId;
    private String styleName;

    private NumericAttributeGroups numeric = new NumericAttributeGroups();
    private StringAttributeGroups nominalMandatory = new StringAttributeGroups();
    private StringAttributeGroups nominalOptional = new StringAttributeGroups();

    @Transient
    private Map<String, Double> distances = Maps.newLinkedHashMap();
    @Transient
    private Map<String, Double> scores = Maps.newLinkedHashMap();

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStyleId() {
        return styleId;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public NumericAttributeGroups getNumeric() {
        return numeric;
    }

    public void setNumeric(NumericAttributeGroups numeric) {
        this.numeric = numeric;
    }

    public StringAttributeGroups getNominalMandatory() {
        return nominalMandatory;
    }

    public void setNominalMandatory(StringAttributeGroups nominalMandatory) {
        this.nominalMandatory = nominalMandatory;
    }

    public StringAttributeGroups getNominalOptional() {
        return nominalOptional;
    }

    public void setNominalOptional(StringAttributeGroups nominalOptional) {
        this.nominalOptional = nominalOptional;
    }

    public Map<String, Double> getDistances() {
        return distances;
    }

    public void setDistances(Map<String, Double> distances) {
        this.distances = distances;
    }

    public Map<String, Double> getScores() {
        return scores;
    }

    public void setScores(Map<String, Double> scores) {
        this.scores = scores;
    }

    public Object readField(Object target, String fieldName) throws IllegalAccessException {
        return FieldUtils.readField(target, fieldName, true);
    }

    public void setAll(AttributableEntity attributableEntity, String groupName) throws IllegalAccessException {
        if (attributableEntity != null) {
            for (String name : attributableEntity.getNumericAbsoluteNames()) {
                this.numeric.getAttribute(groupName, name).setAbsolute((Integer) readField(attributableEntity, name));
            }
            for (String name : attributableEntity.getNominalMandatoryNames()) {
                this.nominalMandatory.setAttribute(groupName, name, readField(attributableEntity, name));
            }
            for (String name : attributableEntity.getNominalOptionalNames()) {
                this.nominalOptional.setAttribute(groupName, name, readField(attributableEntity, name));
            }
        }
    }
}
