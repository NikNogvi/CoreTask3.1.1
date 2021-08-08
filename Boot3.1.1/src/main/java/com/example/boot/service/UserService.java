package com.example.boot.service;

import com.example.boot.DAO.UserDAO;
import com.example.boot.model.Role;
import com.example.boot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }


    public List<User> getAllUsers() {
        return userDAO.findAll();
    }


    public void saveUser(User user) {
        user.setRoles(Collections.singleton(new Role(2L, "ROLE_USER")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }


    public void editUser(User user) {
        if (user.getRoles().contains(new Role(2L, "ROLE_ADMIN"))) {
            user.setRoles(Collections.singleton(new Role(2L, "ROLE_ADMIN")));
        } else {
            user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }


    public User getUserById(long id) {
        return userDAO.findById(id);
    }


    public void deleteUser(long id) {
        userDAO.deleteById(id);
    }


    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

}
