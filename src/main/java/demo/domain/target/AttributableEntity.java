package demo.domain.target;

import demo.domain.source.Entity;

abstract public /*TODO*/ class AttributableEntity extends Entity {

    private static final String[] EMPTY = {};

    protected String[] getNumericAbsoluteNames() {
        return EMPTY;
    }

    protected String[] getNominalMandatoryNames() {
        return EMPTY;
    }

    protected String[] getNominalOptionalNames() {
        return EMPTY;
    }

}
