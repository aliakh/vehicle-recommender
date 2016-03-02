package demo.domain.target;

public /*TODO*/ class IntegerAttributeGroups extends AttributeGroups<Integer> {

    @Override
    public Integer convert(Object attributeValue) {
        return (Integer) attributeValue;
    }
}
