package chatApp.services;


import chatApp.dtos.requests.CreateChatRequest;
import chatApp.dtos.requests.RegisterUserRequest;
import chatApp.dtos.responses.RegisterUserResponse;

public interface UsersServices {

    RegisterUserResponse register(RegisterUserRequest registerUserRequest);

    void createChat(CreateChatRequest createChatRequest);
}
