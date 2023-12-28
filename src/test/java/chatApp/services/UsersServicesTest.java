package chatApp.services;

import chatApp.data.repositories.UsersRepository;
import chatApp.dtos.requests.CreateChatRequest;
import chatApp.dtos.requests.RegisterUserRequest;
import chatApp.dtos.requests.SendMessageRequest;
import chatApp.exceptions.UserAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UsersServicesTest {

    @BeforeEach
    public void deleteAllWith() {
        usersRepository.deleteAll();
        chatServices.deleteAll();
    }

    @Autowired
    private UsersServices usersServices;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ChatServices chatServices;

    @Test
    public void testThatUserCanRegister() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("username");
        registerUserRequest.setPassword("password");
        usersServices.register(registerUserRequest);
        assertThat(usersRepository.count(), is(1L));
    }

    @Test
    public void testThatUserIsUnique() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("username");
        registerUserRequest.setPassword("password");
        usersServices.register(registerUserRequest);
        assertThat(usersRepository.count(), is(1L));

        RegisterUserRequest registerUserRequest1 = new RegisterUserRequest();
        registerUserRequest1.setUsername("username");
        registerUserRequest1.setPassword("password");
        assertThrows(UserAlreadyExistsException.class, ()-> usersServices.register(registerUserRequest1));
    }

    @Test
    public void testThatUserCanCreateChat() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("username");
        registerUserRequest.setPassword("password");
        usersServices.register(registerUserRequest);

        RegisterUserRequest registerUserRequest1 = new RegisterUserRequest();
        registerUserRequest1.setUsername("user_name");
        registerUserRequest1.setPassword("password");
        usersServices.register(registerUserRequest1);

        CreateChatRequest createChatRequest = new CreateChatRequest();
        createChatRequest.setFirstUser("username");
        createChatRequest.setSecondUser("user_name");

        usersServices.createChat(createChatRequest);
        assertThat(chatServices.count(), is(1L));
    }
    
    @Test
    public void testThatChatIsCreatedWhenFirstMessageIsSentByUser() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("username");
        registerUserRequest.setPassword("password");
        usersServices.register(registerUserRequest);

        RegisterUserRequest registerUserRequest1 = new RegisterUserRequest();
        registerUserRequest1.setUsername("user_name");
        registerUserRequest1.setPassword("password");
        usersServices.register(registerUserRequest1);

        CreateChatRequest createChatRequest = new CreateChatRequest();
        createChatRequest.setFirstUser("username");
        createChatRequest.setSecondUser("user_name");
        usersServices.createChat(createChatRequest);

        SendMessageRequest sendMessageRequest = new SendMessageRequest();
        sendMessageRequest.setFrom("user_name");
        sendMessageRequest.setTo("username");
        sendMessageRequest.setMessageBody("Hey there, trust you're doing okay");
//        sendMessageRequest.setTimeSent(LocalDate.now());
//        sendMessageRequest.setDateSent(LocalDate.of(2023, Month.DECEMBER, 27));

        usersServices.sendMessage(sendMessageRequest);
        assertThat(chatServices.count(), is(1L));
    }

}