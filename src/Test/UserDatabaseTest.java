package Test;

import Database.UserDatabase;
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
    void addUserWhenUserIsNullTest() {

    }

    @Test
    void addUserWhenUserExistsTest() {

    }

    @Test
    void addUserWhenUsernameExistsTest() {

    }

    @Test
    void addUserWhenUsernameIsNullTest() {

    }

    @Test
    void addUserWhenUsernameIsEmptyStringTest() {

    }

    @Test
    void saveDatabase() {
    }

    @Test
    void deleteUser() {
    }
}