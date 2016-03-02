package demo.domain.source.property;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import demo.domain.source.Entity;
import demo.domain.target.VehicleException;
import org.springframework.data.annotation.Id;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public /*TODO*/ class Property extends Entity {

    @Id
    private String id;

    @JsonProperty("attributeGroups")
    private Map<String, Attributes> groups;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Attributes> getGroups() {
        return groups;
    }

    public void setGroups(Map<String, Attributes> groups) {
        this.groups = groups;
    }

    public void normalize() {
        if (groups != null) {
            Map<String, Attributes> source = getGroups();
            Map<String, Attributes> target = Maps.newLinkedHashMap();

            for (String key : source.keySet()) {
                Attributes value = groups.get(key);
                key = normalize(key);
                target.put(key, value);
            }

            setGroups(target);
        }
    }

    public Integer getAttribute(String groupName, String attributeName) throws VehicleException {
        Attributes values = groups.get(groupName);
        if (values == null) {
            return null;
        }

        Attribute value = values.getAttributes().get(attributeName);
        if (value == null) {
            return null;
        }

        try {
            return (int) Double.parseDouble(value.getValue());
        } catch (NumberFormatException e) {
            throw new VehicleException("Parsing error: " + groupName + "." + attributeName + "=" + value.getValue());
        }
    }
}
