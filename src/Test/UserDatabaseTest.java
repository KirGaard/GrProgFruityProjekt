package Test;

import DataAccess.DataAccessor;
import Database.UserDatabase;
import Database.UserNotInDatabaseException;
import Presentation.Login.Exceptions.UsernameAlreadyExistsException;
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
    void deleteUserThatDoesNotExistsTest() {
        assertThrows(UserNotInDatabaseException.class, () -> userDatabase.deleteUser(user));
    }
}