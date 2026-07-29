package com.mauricio.controladordeencomendas1.service;

import com.mauricio.controladordeencomendas1.model.User;
import com.mauricio.controladordeencomendas1.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalStateException("Ja existe um usuario cadastrado com este email");
        }

        User user = new User(null, request.role(), request.name(), request.email(),
                passwordEncoder.encode(request.password()));

        return userRepository.save(user);
    }
}
