package demo.domain.target;

import demo.domain.source.property.Property;
import demo.domain.source.style.Style;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.log4j.Logger;

public /*TODO*/ class VehicleBuilder {

    private static final String SPECIFICATIONS = "SPECIFICATIONS";
    private static final String EXTERIOR_DIMENSIONS = "EXTERIOR_DIMENSIONS";

    private final static Logger LOGGER = Logger.getLogger(VehicleBuilder.class);

    public static Vehicle create(Style style, Property property) {
        Vehicle vehicle = new Vehicle();

        try {
            if (style == null) {
                throw new VehicleException("Null: style");
            }

            vehicle.setMakeName(style.getMake().getName());
            vehicle.setModelName(style.getModel().getName());
            vehicle.setYear(style.getYear().getYear());
            vehicle.setStyleId(style.getId());
            vehicle.setStyleName(style.getName());

            if (style.getPrice() == null) {
                throw new VehicleException("Null: style.getPrice()");
            }

            vehicle.getNominalMandatory().setAttribute("common", "drivenWheels", style.getDrivenWheels());
            vehicle.getNominalMandatory().setAttribute("common", "numOfDoors", style.getNumberOfDoors());

            vehicle.setAll(style.getSubmodel(), "submodel");
            vehicle.setAll(style.getEngine(), "engine");
            vehicle.setAll(style.getTransmission(), "transmission");
            vehicle.setAll(style.getMilesPerGallon(), "milesPerGallon");
            vehicle.setAll(style.getPrice(), "price");
            vehicle.setAll(style.getCategories(), "categories");

            if (property == null) {
                throw new VehicleException("Null: property");
            }

            vehicle.getNumeric().getAttribute(SPECIFICATIONS, "weight").setAbsolute(property.getAttribute(SPECIFICATIONS, "CURB_WEIGHT"));

            vehicle.getNumeric().getAttribute(EXTERIOR_DIMENSIONS, "length").setAbsolute(property.getAttribute(EXTERIOR_DIMENSIONS, "OVERALL_LENGTH"));
            vehicle.getNumeric().getAttribute(EXTERIOR_DIMENSIONS, "width").setAbsolute(property.getAttribute(EXTERIOR_DIMENSIONS, "OVERALL_WIDTH_WITHOUT_MIRRORS"));
            vehicle.getNumeric().getAttribute(EXTERIOR_DIMENSIONS, "height").setAbsolute(property.getAttribute(EXTERIOR_DIMENSIONS, "OVERALL_HEIGHT"));

            vehicle.setValid(true);
        } catch (VehicleException e) {
            LOGGER.error(e.getMessage());
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage());
        }

        return vehicle;
    }

    private static Object readField(Object target, String fieldName) throws IllegalAccessException {
        return FieldUtils.readField(target, fieldName, true);
    }
}
