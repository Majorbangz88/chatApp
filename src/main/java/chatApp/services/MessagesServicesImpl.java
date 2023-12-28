package chatApp.services;

import chatApp.data.models.Chats;
import chatApp.dtos.requests.SendMessageRequest;
import org.springframework.stereotype.Service;

@Service
public class MessagesServicesImpl implements MessagesServices{
    @Override
    public void sendMessage(SendMessageRequest sendMessageRequest, Chats chat) {

    }
}
