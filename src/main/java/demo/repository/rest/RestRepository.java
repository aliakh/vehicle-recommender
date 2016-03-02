package demo.repository.rest;

import demo.domain.source.Makes;
import demo.domain.source.Model;
import demo.domain.source.style.Style;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import static demo.repository.rest.GlobalRestConstants.API_KEY;
import static demo.repository.rest.RestConstants.API_VERSION_2_URL;

@Repository
public /*TODO*/ class RestRepository {

    private RestTemplate restTemplate = new RestTemplate();

    public Makes loadMakes() {
        return restTemplate.getForObject(API_VERSION_2_URL + String.format("makes?view=basic&fmt=json&api_key=%s", API_KEY), Makes./*TODO*/ class);
    }

    public Model loadModel(String makeNiceName, String modelNiceName) {
        return restTemplate.getForObject(API_VERSION_2_URL + String.format("%s/%s?view=basic&fmt=json&api_key=%s", makeNiceName, modelNiceName, API_KEY), Model./*TODO*/ class);
    }

    public Style loadStyle(String styleId) {
        return restTemplate.getForObject(API_VERSION_2_URL + String.format("styles/%s?view=full&fmt=json&api_key=%s", styleId, API_KEY), Style./*TODO*/ class);
    }
}
