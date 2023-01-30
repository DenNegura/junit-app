package org.junit.repository;

import org.junit.domain.Status;
import org.junit.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserRepositoryImplTest {

    private UserRepositoryImpl userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepositoryImpl();
    }

    @Test
    public void shouldThrowExceptionWhenUserIdIsNull() {
        // Given
        Integer userId = null;

        // When
        Executable executable = () -> userRepository.findById(userId);

        // Then
        assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    public void shouldReturnEmptyOptionalWhenUserNotFound() {
        // Given
        Integer userId = 100;

        // When
        Optional<User> emptyUser = userRepository.findById(userId);

        // Then
        assertTrue(emptyUser.isEmpty());
    }

    @Test
    public void shouldReturnUserWhenUserWithThisId() {
        // Given
        Integer userId = 1;
        User actualUser = new User(1, "John", Status.ACTIVE);

        // When
        Optional<User> expectedUser = userRepository.findById(userId);

        // Then
        assertTrue(expectedUser.isPresent());
        assertEquals(expectedUser.get(), actualUser);
    }

}