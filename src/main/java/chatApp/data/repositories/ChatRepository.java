package chatApp.data.repositories;

import chatApp.data.models.Chats;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<Chats, String> {
}
