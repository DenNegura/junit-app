package org.junit.service;

import org.junit.domain.Status;
import org.junit.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidationServiceImplTest {

    private ValidationServiceImpl validationService;

    @BeforeEach
    void StartUp() {
        validationService = new ValidationServiceImpl();
    }

    @Test
    void shouldReturnExceptionOnValidateAmount() {
        // Given

        // When
        Executable nullAmount = () -> validationService.validateAmount(null);
        Executable zeroAmount = () -> validationService.validateAmount(0D);
        Executable negativeAmount = () -> validationService.validateAmount(-10D);

        // Then
        assertThrows(IllegalArgumentException.class, nullAmount);
        assertThrows(IllegalArgumentException.class, zeroAmount);
        assertThrows(IllegalArgumentException.class, negativeAmount);
    }

    @Test
    void testValidateAmount() {
        // Given
        double validAmount = 10D;

        // When
        validationService.validateAmount(validAmount);

        // Then
    }

    @Test
    void shouldReturnExceptionOnValidatePaymentId() {
        // Given

        // When
        Executable nullPaymentId = () -> validationService.validatePaymentId(null);

        // Then
        assertThrows(IllegalArgumentException.class, nullPaymentId);
    }

    @Test
    void testValidatePaymentId() {
        // Given
        UUID validPaymentId = UUID.randomUUID();

        // When
        validationService.validatePaymentId(validPaymentId);

        // Then
    }

    @Test
    void shouldReturnExceptionOnValidateUserId() {
        // Given

        // When
        Executable nullUserId = () -> validationService.validateUserId(null);
        // Then
        assertThrows(IllegalArgumentException.class, nullUserId);
    }

    @Test
    void testValidateUserId() {
        // Given
        int validUserId = 10;

        // When
        validationService.validateUserId(validUserId);

        // Then
    }

    @Test
    void shouldReturnExceptionOnValidateUser() {
        // Given
        User user = new User(1, "John", Status.INACTIVE);

        // When
        Executable inactiveUser = () -> validationService.validateUser(user);

        // Then
        assertThrows(IllegalArgumentException.class, inactiveUser);
    }

    @Test
    void testValidateUser() {
        // Given
        User user = new User(1, "John", Status.ACTIVE);

        // When
        validationService.validateUser(user);

        // Then
    }

    @Test
    void shouldReturnExceptionOnValidateMessage() {
        // Given
        String message = null;

        // When
        Executable nullMessage = () -> validationService.validateMessage(message);

        // Then
        assertThrows(IllegalArgumentException.class, nullMessage);
    }

    @Test
    void testValidateMessage() {
        // Given
        String validMessage = "message";

        // When
        validationService.validateMessage(validMessage);

        // Then
    }
}