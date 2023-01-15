package ru.mail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleTest {

    @Disabled
    @DisplayName("Проверка, что 3>2")
    @Test
    void firstTest() {
        assertTrue(3>2);
    }

    @DisplayName("Проверка, что 3>1")
    @Test
    void seconfTest() {
        assertTrue(3>1);
    }
}
