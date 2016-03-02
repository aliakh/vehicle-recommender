package demo.domain.target;

public /*TODO*/ class StringAttributeGroups extends AttributeGroups<String> {

    @Override
    public String convert(Object attributeValue) {
        return "" + attributeValue;
    }
}
