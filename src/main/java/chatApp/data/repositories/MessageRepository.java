package chatApp.data.repositories;

import chatApp.data.models.Messages;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Messages, String> {
}
