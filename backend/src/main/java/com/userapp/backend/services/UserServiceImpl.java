package com.userapp.backend.services;

import com.userapp.backend.entities.Role;
import com.userapp.backend.entities.User;
import com.userapp.backend.models.UserRequest;
import com.userapp.backend.models.iUser;
import com.userapp.backend.repositories.RolRepository;
import com.userapp.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) this.userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(@NonNull Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public User save(User user) {

        user.setRoles(getRoles(user));
        // obtengo password, lo codifico y se lo asigno nuevamente al usuario
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public Optional<User> update(UserRequest user, Long id) {

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User userDb = userOptional.get();
            userDb.setEmail(user.getEmail());
            userDb.setLastname(user.getLastname());
            userDb.setName(user.getName());
            userDb.setUsername(user.getUsername());

            userDb.setRoles(getRoles(user));
            return Optional.of(userRepository.save(userDb));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    private List<Role> getRoles(iUser user) {
        List<Role> roles = new ArrayList<>();
        Optional<Role> optionalRoleUser = rolRepository.findByName("ROLE_USER");
        optionalRoleUser.ifPresent(roles::add);

        if (user.isAdmin()) {
            Optional<Role> optionalRoleAdmin = rolRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }
        return roles;
    }
}
