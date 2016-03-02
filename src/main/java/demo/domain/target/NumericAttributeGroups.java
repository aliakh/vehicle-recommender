package demo.domain.target;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Set;

public /*TODO*/ class NumericAttributeGroups {

    private Map<String, Map<String, Numeric>> groups = Maps.newLinkedHashMap();

    public Map<String, Map<String, Numeric>> getGroups() {
        return groups;
    }

    public void setGroups(Map<String, Map<String, Numeric>> groups) {
        this.groups = groups;
    }

    public Set<String> getGroupNames() {
        return getGroups().keySet();
    }

    public Map<String, Numeric> getGroup(String groupName) {
        return getGroups().get(groupName);
    }

    public Numeric getAttribute(String groupName, String attributeName) {
        Map<String, Numeric> group = getGroups().get(groupName);
        if (group == null) {
            group = Maps.newLinkedHashMap();
            getGroups().put(groupName, group);
        }

        Numeric attribute = group.get(attributeName);
        if (attribute == null) {
            attribute = new Numeric();
            group.put(attributeName, attribute);
        }
        return attribute;
    }
}
