package hcmute.it.furnitureshop.Service.Impl;
import hcmute.it.furnitureshop.Entity.User;
import hcmute.it.furnitureshop.Service.UserService;
import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class UserServiceImplTest extends TestCase {
    @Autowired
    UserService userService;
    @BeforeEach
    protected void setUp() throws Exception {
        System.out.println("Setup");
    }
    @AfterEach
    protected void tearDown() throws Exception {
        System.out.println("Teardown");
    }
    @AfterAll
    static void afterAll() {
        System.out.println("close");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("start");
    }
    @Test
    void findByPhone() {
        Optional<User> user = userService.findByPhone("0865762251");
        assertEquals("Thuận Phát",user.get().getName());
        System.out.println("test findByPhone success");
    }
    @Test
    void findById() {
        Optional<User> user = userService.findById(83);
        assertEquals("Thuận Phát",user.get().getName());
        System.out.println("test findById success");
    }
}