package org.junit.service;

import lombok.AllArgsConstructor;
import org.junit.domain.Payment;
import org.junit.domain.User;
import org.junit.repository.PaymentRepository;
import org.junit.repository.UserRepository;

import java.util.NoSuchElementException;
import java.util.UUID;

@AllArgsConstructor
public class PaymentService {

    private UserRepository userRepository;

    private PaymentRepository paymentRepository;

    private ValidationService validationService;

    public Payment createPayment(Integer userId, Double amount) {
        validationService.validateUserId(userId);
        validationService.validateAmount(amount);

        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User with id " + userId + " not found"));
        validationService.validateUser(user);

        final String paymentMessage = "Payment from user " + user.getName();
        final Payment payment = new Payment(user.getId(), amount, paymentMessage);
        return paymentRepository.save(payment);
    }

    public Payment editPaymentMessage(UUID paymentId, String newMessage) {
        validationService.validatePaymentId(paymentId);
        validationService.validateMessage(newMessage);

        return paymentRepository.editMessage(paymentId, newMessage);
    }
}
