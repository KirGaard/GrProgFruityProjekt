package Test;

import DataAccess.DataAccessor;
import Database.UserDatabase;
import User.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDatabaseTest {
    private UserDatabase userDatabase;

    @BeforeEach
    void setUp() {
        userDatabase = new UserDatabase();
    }

    @AfterEach
    void tearDown() {
        userDatabase = null;
    }

    @Test
    void addUserWhenUserExistsTest() {
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