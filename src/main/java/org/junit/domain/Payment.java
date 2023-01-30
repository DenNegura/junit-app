package org.junit.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Payment {

    private final UUID paymentId;

    private final Integer userId;

    private final Double amount;

    private String message;

    public Payment(Integer userId, Double amount, String message) {
        this (UUID.randomUUID(), userId, amount, message);
    }

    public static Payment copyOf(Payment originalPayment) {
        return new Payment(originalPayment.paymentId, originalPayment.userId, originalPayment.amount, originalPayment.message);
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
