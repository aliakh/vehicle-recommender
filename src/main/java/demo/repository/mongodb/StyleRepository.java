package demo.repository.mongodb;

import demo.domain.source.style.Style;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StyleRepository extends MongoRepository<Style, String> {
}
