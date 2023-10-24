package chatApp.data.repositories;

import chatApp.data.models.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @Test
    public void testThatRepositorySavesUserSuccessfully() {
        Users user = new Users();
        usersRepository.save(user);
        assertThat(user.getId(), is(notNullValue()));
    }
}
