package com.experience.user.service;

import com.experience.user.model.Users;
import com.experience.user.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users createUser(Users user) {
        // Verificar si el correo electrónico ya existe en la base de datos
        if (usersRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("El correo electrónico ya está registrado");
        }

        // Si el correo electrónico no existe, guardar el nuevo usuario
        return usersRepository.save(user);
    }
}
