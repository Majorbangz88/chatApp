package chatApp.services;

import chatApp.data.models.Chats;
import chatApp.data.models.Users;
import chatApp.data.repositories.UsersRepository;
import chatApp.dtos.requests.CreateChatRequest;
import chatApp.dtos.requests.RegisterUserRequest;
import chatApp.dtos.responses.RegisterUserResponse;
import chatApp.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static chatApp.utils.Mapper.map;

@Service
public class UsersServicesImpl implements UsersServices{

    @Autowired
    private UsersRepository usersRepository;

    private ChatServices chatServices;

    @Override
    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) {
        findUser(registerUserRequest);
        return map(usersRepository.save(map(registerUserRequest)));
    }

    @Override
    public void createChat(CreateChatRequest createChatRequest) {
        Chats chat = new Chats();
        chat.setChatName(chat.getChatName());
        chat.
    }

    private void findUser(RegisterUserRequest registerUserRequest) {
        Optional<Users> users = usersRepository.findByEmail(registerUserRequest.getUsername());
        if (users.isPresent())
            throw new UserAlreadyExistsException("User exists already!!");
    }


}
