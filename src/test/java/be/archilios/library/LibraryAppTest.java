package be.archilios.library;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class LibraryAppTest {
    @Test
    void main_runsSpringApplication() {
        assertDoesNotThrow(() -> LibraryApp.main(new String[]{}));
    }
    
    @Test
    void contextLoads() {
        assertDoesNotThrow(() -> {});
    }
}
