package demo.domain.source.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import demo.domain.source.Entity;

import java.util.List;

public /*TODO*/ class Properties extends Entity {

    @JsonProperty("styleHolder")
    private List<Property> properties;

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public void normalize() {
        if (properties != null) {
            for (Property style : properties) {
                style.normalize();
            }
        }
    }

    public List<String> getIds() {
        List<String> ids = Lists.newArrayList();
        for (Property styleHolderItem : properties) {
            ids.add(styleHolderItem.getId());
        }
        return ids;
    }
}
