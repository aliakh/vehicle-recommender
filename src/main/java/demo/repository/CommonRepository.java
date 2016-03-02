package demo.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class CommonRepository {

    @Autowired
    private MongoOperations mongoOperations;

    private static final String VEHICLE_COLLECTION_NAME = "vehicle";

    private static final String MAKE_NAME = "makeName";
    private static final String MODEL_NAME = "modelName";
    private static final String YEAR = "year";
    private static final String STYLE_NAME = "styleName";

    public List<String> getMakes() {
        List<String> makes = mongoOperations.getCollection(VEHICLE_COLLECTION_NAME).distinct(MAKE_NAME);
        Collections.sort(makes);
        return makes;
    }

    public List<String> getModels(String make) {
        DBObject query = new BasicDBObject();
        query.put(MAKE_NAME, make);

        List<String> models = mongoOperations.getCollection(VEHICLE_COLLECTION_NAME).distinct(MODEL_NAME, query);
        Collections.sort(models);
        return models;
    }

    public List<String> getYears(String make, String model) {
        DBObject query = new BasicDBObject();
        query.put(MAKE_NAME, make);
        query.put(MODEL_NAME, model);

        List<String> years = mongoOperations.getCollection(VEHICLE_COLLECTION_NAME).distinct(YEAR, query);
        Collections.sort(years);
        return years;
    }

    public List<Map> getStyles(String make, String model, String year) {
        Sort sort = new Sort(Sort.Direction.ASC, STYLE_NAME);
        Criteria criteria = Criteria.where(MAKE_NAME).is(make).and(MODEL_NAME).is(model).and(YEAR).is(year);
        Query query = Query.query(criteria).with(sort);
        query.fields().include(STYLE_NAME);
        return mongoOperations.find(query, Map.class, VEHICLE_COLLECTION_NAME);
    }
}
