package demo.domain.source.style;

import demo.domain.source.Entity;

import java.util.List;

public class Color extends Entity {

    private String category;
    private List<ColorItem> options;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<ColorItem> getOptions() {
        return options;
    }

    public void setOptions(List<ColorItem> options) {
        this.options = options;
    }
}
