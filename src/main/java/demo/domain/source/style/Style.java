package demo.domain.source.style;

import com.fasterxml.jackson.annotation.JsonProperty;
import demo.domain.source.Entity;
import demo.domain.source.Make;
import demo.domain.source.Model;
import demo.domain.source.Year;
import org.springframework.data.annotation.Id;

import java.util.List;

public class Style extends Entity {

    @Id
    private String id;
    private Make make;
    private Model model;
    private Year year;
    private String name;
    private String manufacturerCode;
    private Submodel submodel;
    private String trim;
    private Engine engine;
    private Transmission transmission;
    private String drivenWheels;
    @JsonProperty("numOfDoors")
    private String numberOfDoors;
    private List<Option> options;
    private List<Color> colors;
    private Price price;
    private Categories categories;
    private List<String> states;
    private List<String> squishVins;
    @JsonProperty("MPG")
    private MilesPerGallon milesPerGallon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturerCode() {
        return manufacturerCode;
    }

    public void setManufacturerCode(String manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
    }

    public Submodel getSubmodel() {
        return submodel;
    }

    public void setSubmodel(Submodel submodel) {
        this.submodel = submodel;
    }

    public String getTrim() {
        return trim;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public String getDrivenWheels() {
        return drivenWheels;
    }

    public void setDrivenWheels(String drivenWheels) {
        this.drivenWheels = drivenWheels;
    }

    public String getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(String numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public List<Color> getColors() {
        return colors;
    }

    public void setColors(List<Color> colors) {
        this.colors = colors;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public List<String> getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }

    public List<String> getSquishVins() {
        return squishVins;
    }

    public void setSquishVins(List<String> squishVins) {
        this.squishVins = squishVins;
    }

    public MilesPerGallon getMilesPerGallon() {
        return milesPerGallon;
    }

    public void setMilesPerGallon(MilesPerGallon milesPerGallon) {
        this.milesPerGallon = milesPerGallon;
    }
}
