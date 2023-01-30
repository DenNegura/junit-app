package org.junit.repository;

import org.junit.domain.Payment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {

    Optional<Payment> findById(UUID paymentId);

    List<Payment> findAll();

    Payment save(Payment payment);

    Payment editMessage(UUID paymentId, String message);

}
