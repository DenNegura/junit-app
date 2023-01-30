package org.junit.repository;

import org.junit.domain.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Integer id);
}
