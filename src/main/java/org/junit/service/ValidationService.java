package org.junit.service;

import org.junit.domain.User;

import java.util.UUID;

public interface ValidationService {

    void validateAmount(Double amount);

    void validatePaymentId(UUID paymentId);

    void validateUserId(Integer userId);

    void validateUser(User user);

    void validateMessage (String message);
}
