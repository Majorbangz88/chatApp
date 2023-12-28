package chatApp.data.repositories;

import chatApp.data.models.Chats;
import chatApp.data.models.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends MongoRepository<Chats, String> {
    Optional<Chats> findChatByChatNameAndParticipantsContains(String chatName, List<Users> participants);
}
