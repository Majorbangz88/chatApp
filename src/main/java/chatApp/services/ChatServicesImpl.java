package chatApp.services;

import chatApp.data.models.Chats;
import chatApp.data.repositories.ChatRepository;
import chatApp.dtos.requests.FindChatRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatServicesImpl implements ChatServices{

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public long count() {
        return chatRepository.count();
    }

    @Override
    public void createChat(Chats chat) {
        chatRepository.save(chat);
    }

    @Override
    public void deleteAll() {
        chatRepository.deleteAll();
    }

    @Override
    public Chats findChat(FindChatRequest findChatRequest) {
        Optional<Chats> chat = chatRepository.
                findChatByChatNameAndParticipantsContains(
                        findChatRequest.getFirstChatName(),
                        List.of(findChatRequest.getSecondChatName()));
        if (chat.isPresent())
            return chat.get();
        Optional<Chats> theSameChat = checkRepositoryAgain(findChatRequest);
        return theSameChat.orElse(null);
    }

    private Optional<Chats> checkRepositoryAgain(FindChatRequest findChatRequest) {
        return chatRepository.findChatByChatNameAndParticipantsContains(
                findChatRequest.getFirstChatName(), findChatRequest.getSecondChatName());
    }
}
