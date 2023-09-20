import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class UserClientTest {
    @Test
    void testGetUsers() {
        try {
            List<User> users = UserClient.getUsersFromPage(1);
            Assertions.assertNotNull(users);
            Assertions.assertEquals(users.size(), 6);
            System.out.println(users);
            User firstUser = users.get(0);
            Assertions.assertEquals(firstUser.getFirstName(), "George");

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("failed with exceptions");
        }
    }
}