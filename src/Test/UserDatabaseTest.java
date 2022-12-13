package Test;

import DataAccess.DataAccessor;
import Database.UserDatabase;
import Presentation.Login.InvalidUserException;
import User.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDatabaseTest {
    private UserDatabase userDatabase;
    private User user;

    @BeforeEach
    void setUp() {
        userDatabase = new UserDatabase();
        user = new User("Lars", "123");
    }

    @AfterEach
    void tearDown() {
        userDatabase = null;
        user = null;
    }

    @Test
    void addUserWhenUserExistsTest() {
        userDatabase.addUser(user);
        assertThrows(UsernameAlreadyExistsException.class, () -> userDatabase.addUser(user));
    }

    @Test
    void addUserWhenUsernameExistsTest() {

    }

    @Test
    void addUserWhenUsernameIsEmptyStringTest() {

    }

    @Test
    void saveDatabase() {

    }

    @Test
    void deleteWhenUsernameDoesNotExistsTest() {

    }
}