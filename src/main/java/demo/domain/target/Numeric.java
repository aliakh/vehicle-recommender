package demo.domain.target;

import demo.domain.source.Entity;
import org.springframework.data.annotation.Transient;

public /*TODO*/ class Numeric extends Entity {

    private Integer absolute;

    @Transient
    private Double normalized;

    @Transient
    private Integer percent;

    public Integer getAbsolute() {
        return absolute;
    }

    public void setAbsolute(Integer absolute) {
        this.absolute = absolute;
    }

    public Double getNormalized() {
        return normalized;
    }

    public void setNormalized(Double normalized) {
        this.normalized = normalized;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }
}
