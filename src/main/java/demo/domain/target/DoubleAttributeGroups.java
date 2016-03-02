package demo.domain.target;

public /*TODO*/ class DoubleAttributeGroups extends AttributeGroups<Double> {

    @Override
    public Double convert(Object attributeValue) {
        return (Double) attributeValue;
    }
}
