package chatApp.services;

import chatApp.data.models.Chats;
import chatApp.data.models.Users;
import chatApp.data.repositories.UsersRepository;
import chatApp.dtos.requests.CreateChatRequest;
import chatApp.dtos.requests.FindChatRequest;
import chatApp.dtos.requests.RegisterUserRequest;
import chatApp.dtos.requests.SendMessageRequest;
import chatApp.dtos.responses.RegisterUserResponse;
import chatApp.exceptions.InvalidUsernameOrPasswordException;
import chatApp.exceptions.UserAlreadyExistsException;
import chatApp.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static chatApp.utils.Mapper.map;

@Service
public class UsersServicesImpl implements UsersServices{

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ChatServices chatServices;

    @Autowired
    private MessagesServices messagesServices;

    @Override
    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) {
        validateRequestNotEmpty(registerUserRequest);
        findUser(registerUserRequest);
        return map(usersRepository.save(map(registerUserRequest)));
    }

    private void validateRequestNotEmpty(RegisterUserRequest registerUserRequest) {
        boolean isNullUsername = registerUserRequest.getUsername() == null;
        boolean isNullPassword = registerUserRequest.getPassword() == null;
        if (isNullUsername || isNullPassword)
            throw new InvalidUsernameOrPasswordException("Username or password is invalid");
    }

    @Override
    public Chats createChat(CreateChatRequest createChatRequest) {
        Chats chat = new Chats();
        chat.setChatName(createChatRequest.getFirstUser() + " " + createChatRequest.getSecondUser());
        chat.getParticipants().addAll(List.of(findByEmail(createChatRequest.getFirstUser()), findByEmail(createChatRequest.getSecondUser())));
        chatServices.createChat(chat);
        return chat;
    }

    @Override
    public void sendMessage(SendMessageRequest sendMessageRequest) {
        CreateChatRequest chatRequest = map(sendMessageRequest);
        Chats chat = findChatWithUsers(sendMessageRequest.getFrom(), sendMessageRequest.getTo());
        if (chat == null)
            chat = createChat(chatRequest);
        postMessage(sendMessageRequest, chat);
    }

    private Chats findChatWithUsers(String firstUser, String secondUser) {
        FindChatRequest findChatRequest = new FindChatRequest();
        findChatRequest.setFirstChatName(firstUser + " " + secondUser);
        findChatRequest.setSecondChatName(secondUser + " " + firstUser);
        findChatRequest.setParticipant(List.of(findByEmail(firstUser), findByEmail(secondUser)));
        Chats chat = findChat(findChatRequest);
        return chat;
    }

    private Chats findChat(FindChatRequest findChatRequest) {
        return chatServices.findChat(findChatRequest);
    }

    private Users findByEmail(String username) {
        Optional<Users> foundUser = usersRepository.findByEmail(username);
        if (foundUser.isEmpty())
            throw new UserNotFoundException("User does not exist");
        return foundUser.get();
    }

    private void findUser(RegisterUserRequest registerUserRequest) {
        Optional<Users> users = usersRepository.findByEmail(registerUserRequest.getUsername());
        if (users.isPresent())
            throw new UserAlreadyExistsException("User exists already!!");
    }

    private void postMessage(SendMessageRequest sendMessageRequest, Chats chat) {
        messagesServices.sendMessage(sendMessageRequest, chat);
    }


}
