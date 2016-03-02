package demo.domain.source.style;

import demo.domain.source.Entity;

import java.util.List;

public class Option extends Entity {

    private String category;
    private List<OptionItem> options;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<OptionItem> getOptions() {
        return options;
    }

    public void setOptions(List<OptionItem> options) {
        this.options = options;
    }
}
