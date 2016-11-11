package demo.repository.rest;

import demo.domain.source.property.Properties;
import demo.domain.source.style.Style;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import static demo.repository.rest.RestConstants.API_KEY;
import static demo.repository.rest.RestConstants.API_VERSION_1_URL;
import static demo.repository.rest.RestConstants.API_VERSION_2_URL;

@Repository
public /*TODO*/ class AsyncRestRepository {

    private final AsyncRestTemplate restTemplate;

    public AsyncRestRepository() {
        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
        executor.setConcurrencyLimit(100);
        restTemplate = new AsyncRestTemplate(executor); // new HttpComponentsAsyncClientHttpRequestFactory()
    }

    public ListenableFuture<ResponseEntity<Style>> getStyle(String styleId) {
        return restTemplate.getForEntity(API_VERSION_2_URL + String.format("styles/%s?view=full&fmt=json&api_key=%s", styleId, API_KEY), Style./*TODO*/ class);
    }

    public ListenableFuture<ResponseEntity<Properties>> getStyleHolder(String styleId) {
        return restTemplate.getForEntity(API_VERSION_1_URL + String.format("style/%s?fmt=json&api_key=%s", styleId, API_KEY), Properties./*TODO*/ class);
    }
}
