package chatApp.services;

import chatApp.data.models.Chats;
import chatApp.dtos.requests.FindChatRequest;

public interface ChatServices {
    long count();

    void createChat(Chats chat);

    void deleteAll();

    Chats findChat(FindChatRequest findChatRequest);
}
