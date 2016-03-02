package demo.repository.mongodb;

import demo.domain.source.Make;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MakeRepository extends MongoRepository<Make, String> {
}
