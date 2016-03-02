package demo.domain.source;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.List;

abstract public /*TODO*/ class Entity {

    protected String normalize(String value) {
        if (value != null) {
            value = value.replaceAll("\\.", "");
        }
        return value;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
