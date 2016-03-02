package demo.domain.source.style;

import demo.domain.source.Entity;

import java.util.List;

public class ColorItem extends Entity {

    private String id;
    private String name;
    private String equipmentType;
    private String manufactureOptionName;
    private String manufactureOptionCode;
    private ColorChips colorChips;
    private List<FabricType> fabricTypes;
    private Price price;

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

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getManufactureOptionName() {
        return manufactureOptionName;
    }

    public void setManufactureOptionName(String manufactureOptionName) {
        this.manufactureOptionName = manufactureOptionName;
    }

    public String getManufactureOptionCode() {
        return manufactureOptionCode;
    }

    public void setManufactureOptionCode(String manufactureOptionCode) {
        this.manufactureOptionCode = manufactureOptionCode;
    }

    public ColorChips getColorChips() {
        return colorChips;
    }

    public void setColorChips(ColorChips colorChips) {
        this.colorChips = colorChips;
    }

    public List<FabricType> getFabricTypes() {
        return fabricTypes;
    }

    public void setFabricTypes(List<FabricType> fabricTypes) {
        this.fabricTypes = fabricTypes;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
