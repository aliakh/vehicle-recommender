package demo.domain.source.style;

import demo.domain.source.Entity;

public class ColorChips extends Entity {

    private ColorChip primary;
    private ColorChip secondary;

    public ColorChip getPrimary() {
        return primary;
    }

    public void setPrimary(ColorChip primary) {
        this.primary = primary;
    }

    public ColorChip getSecondary() {
        return secondary;
    }

    public void setSecondary(ColorChip secondary) {
        this.secondary = secondary;
    }
}
