package demo.repository.rest;

import demo.domain.source.Makes;
import demo.domain.source.Model;
import demo.domain.source.style.Style;
import demo.util.Delay;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import static demo.repository.rest.RestConstants.API_KEY;
import static demo.repository.rest.RestConstants.API_VERSION_2_URL;

@Repository
public class RestRepository {

    private RestTemplate restTemplate = new RestTemplate();

    public Makes loadMakes() {
        return getForObject(API_VERSION_2_URL + String.format("makes?view=basic&fmt=json&api_key=%s", API_KEY), Makes.class);
    }

    public Model loadModel(String makeNiceName, String modelNiceName) {
        return getForObject(API_VERSION_2_URL + String.format("%s/%s?view=basic&fmt=json&api_key=%s", makeNiceName, modelNiceName, API_KEY), Model.class);
    }

    public Style loadStyle(String styleId) {
        return getForObject(API_VERSION_2_URL + String.format("styles/%s?view=full&fmt=json&api_key=%s", styleId, API_KEY), Style.class);
    }

    private <T> T getForObject(String url, Class<T> responseType) {
        Delay.limitRate();
        return restTemplate.getForObject(url, responseType);
    }
}
