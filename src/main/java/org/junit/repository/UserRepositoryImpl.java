package org.junit.repository;

import org.junit.domain.Status;
import org.junit.domain.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private List<User> userList;

    public UserRepositoryImpl() {
        userList = Arrays.asList(
                new User(1, "John", Status.ACTIVE),
                new User(2, "Maria", Status.ACTIVE),
                new User(3, "Peter", Status.INACTIVE),
                new User(4, "Anna", Status.ACTIVE),
                new User(5, "David", Status.INACTIVE));
    }

    @Override
    public Optional<User> findById(Integer userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User id must not be null");
        }
        return userList.stream().filter(user -> userId.equals(user.getId())).findFirst();
    }
}
