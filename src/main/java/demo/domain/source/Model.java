package demo.domain.source;

import java.util.List;

public class Model extends Entity {

    private String id;
    private String name;
    private String niceName;
    private List<ModelYear> years;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNiceName() {
        return niceName;
    }

    public void setNiceName(String niceName) {
        this.niceName = niceName;
    }

    public List<ModelYear> getYears() {
        return years;
    }

    public void setYears(List<ModelYear> years) {
        this.years = years;
    }
}
