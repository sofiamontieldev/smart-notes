package com.montiel.smartnotes.service.impl;

import com.montiel.smartnotes.model.entity.User;
import com.montiel.smartnotes.exception.MyValidationException;
import com.montiel.smartnotes.repository.UserRepository;
import com.montiel.smartnotes.service.api.UserService;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {

        validate(user);
        user.setId(null);

        userRepository.save(user);
    }

    public void validate(User user) {

        if (Objects.isNull(user.getId())) {
            throw new MyValidationException("El setId no puede estar vacio");
        }

        if (Objects.isNull(user.getName()) || StringUtils.isEmpty(user.getName())) {
            throw new MyValidationException("El nombre no puede ser nulo o estar vacío");
        }
        if (Objects.isNull(user.getEmail()) || StringUtils.isEmpty(user.getEmail())) {
            throw new MyValidationException("El email no puede ser nulo o estar vacío");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new MyValidationException("El email ya existe");
        }

        if (Objects.isNull(user.getPassword()) || StringUtils.isEmpty(user.getPassword())) {
            throw new MyValidationException("La contraseña no puede ser nula o estar vacía");
        }

    }
}
