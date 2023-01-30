package org.junit.service;

import org.junit.domain.Payment;
import org.junit.domain.Status;
import org.junit.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.repository.PaymentRepositoryImpl;
import org.junit.repository.UserRepositoryImpl;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private UserRepositoryImpl userRepository;
    @Mock
    private PaymentRepositoryImpl paymentRepository;
    @Mock
    private ValidationServiceImpl validationService;
    @InjectMocks
    private PaymentService paymentService;

    PaymentServiceTest() {
    }

    @Test
    void createPayment() {
        // Given
        double amount = 1D;
        String message = "Payment from user John";
        User user = new User(1, "John", Status.ACTIVE);
        Payment expectedPayment = new Payment(user.getId(), amount, message);
        ArgumentCaptor<Payment> paymentCaptor = ArgumentCaptor.forClass(Payment.class);

        // When
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(paymentRepository.save(any(Payment.class))).thenReturn(expectedPayment);
        paymentService.createPayment(user.getId(), amount);
        verify(paymentRepository).save(paymentCaptor.capture());

        // Then
        assertEquals(expectedPayment.getUserId(), paymentCaptor.getValue().getUserId());
        assertEquals(expectedPayment.getAmount(), paymentCaptor.getValue().getAmount());
        assertEquals(expectedPayment.getMessage(), paymentCaptor.getValue().getMessage());
        verify(validationService).validateUserId(user.getId());
        verify(validationService).validateAmount(amount);
        verify(validationService).validateUser(user);
        verifyNoMoreInteractions(validationService);
    }

    @Test
    void shouldReturnExceptionWhenCreatePayment() {
        //Given
        User user = new User(1, "John", Status.INACTIVE);

        //When
        when(userRepository.findById(2)).thenReturn(Optional.empty());
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        doThrow(IllegalArgumentException.class).when(validationService).validateUserId(null);
        doThrow(IllegalArgumentException.class).when(validationService).validateAmount(null);
        doThrow(IllegalArgumentException.class).when(validationService).validateUser(user);

        Executable nullIdUser = () -> paymentService.createPayment(null, 1D);
        Executable nullAmount = () -> paymentService.createPayment(user.getId(), null);
        Executable notFoundUser = () -> paymentService.createPayment(2, 1D);
        Executable inactiveUser = () -> paymentService.createPayment(user.getId(), 1D);

        //Then
        assertThrows(IllegalArgumentException.class, nullIdUser);
        assertThrows(IllegalArgumentException.class, nullAmount);
        assertThrows(NoSuchElementException.class, notFoundUser);
        assertThrows(IllegalArgumentException.class, inactiveUser);
        verify(userRepository).findById(user.getId());
        verify(userRepository).findById(2);
        verify(validationService).validateUserId(null);
        verify(validationService).validateAmount(null);
        verify(validationService).validateUser(user);
    }

    @Test
    void editMessage() {
        //Given
        String oldMessage = "old massage";
        String newMessage = "new message";
        Payment paymentOldMsg = new Payment(1, 1D, oldMessage);
        Payment paymentNewMsg = new Payment(1, 1D, newMessage);

        //When
        when(paymentRepository.editMessage(paymentOldMsg.getPaymentId(), newMessage))
                .thenReturn(paymentNewMsg);
        Payment result = paymentService
                .editPaymentMessage(paymentOldMsg.getPaymentId(), newMessage);

        //Then
        assertEquals(result, paymentNewMsg);
        verify(validationService).validatePaymentId(paymentOldMsg.getPaymentId());
        verify(validationService).validateMessage(paymentNewMsg.getMessage());
    }
}