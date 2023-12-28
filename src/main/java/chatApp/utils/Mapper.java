package chatApp.utils;

import chatApp.data.models.Users;
import chatApp.dtos.requests.CreateChatRequest;
import chatApp.dtos.requests.RegisterUserRequest;
import chatApp.dtos.requests.SendMessageRequest;
import chatApp.dtos.responses.RegisterUserResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mapper {

    public static Users map(RegisterUserRequest registerUserRequest) {
        Users users = new Users();
        users.setEmail(registerUserRequest.getUsername());
        users.setPassword(registerUserRequest.getPassword());
        return users;
    }

    public static RegisterUserResponse map(Users user) {
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setUsername(user.getEmail());
        registerUserResponse.setPassword(user.getPassword());
        registerUserResponse.setRegistrationDate(DateTimeFormatter
                .ofPattern("EEEE dd/MMM/yyy HH:mm:ss a")
                .format(LocalDateTime.now()));
        return registerUserResponse;
    }

    public static CreateChatRequest map(SendMessageRequest sendMessageRequest) {
        CreateChatRequest createChatRequest = new CreateChatRequest();
        createChatRequest.setFirstUser(sendMessageRequest.getFrom());
        createChatRequest.setSecondUser(sendMessageRequest.getTo());
        return createChatRequest;
    }
}
