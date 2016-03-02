package demo.domain.source.property;

import demo.domain.source.Entity;

import java.util.Map;

public class Attributes extends Entity {

    private long id;
    private String name;
    private Map<String, Attribute> attributes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = normalize(name);
    }

    public Map<String, Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Attribute> attributes) {
        this.attributes = attributes;
    }
}
