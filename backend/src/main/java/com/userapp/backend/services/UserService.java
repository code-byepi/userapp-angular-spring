package com.userapp.backend.services;
import com.userapp.backend.entities.User;
import com.userapp.backend.models.UserRequest;
import org.springframework.lang.NonNull;
import java.util.List;
import java.util.Optional;


public interface UserService {

    List<User> findAll();
    Optional<User> findById(@NonNull Long id);
    User save(User user);
    Optional<User> update(UserRequest user, Long id);
    void deleteById(Long id);
}
