package chatApp.services;


import chatApp.data.models.Chats;
import chatApp.dtos.requests.CreateChatRequest;
import chatApp.dtos.requests.RegisterUserRequest;
import chatApp.dtos.requests.SendMessageRequest;
import chatApp.dtos.responses.RegisterUserResponse;

public interface UsersServices {

    RegisterUserResponse register(RegisterUserRequest registerUserRequest);

    Chats createChat(CreateChatRequest createChatRequest);

    void sendMessage(SendMessageRequest sendMessageRequest);
}
