package demo.domain.source;

import java.util.List;

public /*TODO*/ class Makes extends Entity {

    private List<Make> makes;
    private Integer makesCount;

    public List<Make> getMakes() {
        return makes;
    }

    public void setMakes(List<Make> makes) {
        this.makes = makes;
    }

    public int getMakesCount() {
        return makesCount;
    }

    public void setMakesCount(int makesCount) {
        this.makesCount = makesCount;
    }
}
