package demo.domain.source;

import demo.domain.source.style.Style;

import java.util.List;

public class ModelYear extends Entity {

    private String id;
    private String year;
    private List<Style> styles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<Style> getStyles() {
        return styles;
    }

    public void setStyles(List<Style> styles) {
        this.styles = styles;
    }
}
