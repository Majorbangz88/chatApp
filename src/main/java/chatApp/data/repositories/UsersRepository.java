package chatApp.data.repositories;

import chatApp.data.models.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsersRepository extends MongoRepository<Users, String> {

    Optional<Users> findByEmail(String username);
}
