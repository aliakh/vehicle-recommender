package demo.domain.target;

import com.google.common.collect.Maps;
import demo.domain.source.Entity;

import java.util.Map;
import java.util.Set;

abstract public /*TODO*/ class AttributeGroups<T> extends Entity {

    private Map<String, Map<String, T>> groups = Maps.newLinkedHashMap();

    public Map<String, Map<String, T>> getGroups() {
        return groups;
    }

    public void setGroups(Map<String, Map<String, T>> groups) {
        this.groups = groups;
    }

    public Set<String> getGroupNames() {
        return getGroups().keySet();
    }

    public Map<String, T> getGroup(String groupName) {
        return getGroups().get(groupName);
    }

    public T getAttribute(String groupName, String attributeName) {
        Map<String, T> group = getGroups().get(groupName);
        if (group == null) {
            return null;
        }
        return group.get(attributeName);
    }

    public void setAttribute(String groupName, String attributeName, Object attributeValue) {
        Map<String, T> group = getGroups().get(groupName);
        if (group == null) {
            group = Maps.newLinkedHashMap();
            getGroups().put(groupName, group);
        }
        group.put(attributeName, (attributeValue == null ? null : convert(attributeValue)));
    }

    abstract public T convert(Object attributeValue);
}
