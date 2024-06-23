package com.lautypetelin.LoginWithSpringBoot.services;

import com.lautypetelin.LoginWithSpringBoot.models.User;
import com.lautypetelin.LoginWithSpringBoot.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<User> getUsers() {

        return userRepository.findAll();
    }

    @Override
    public void register(String firstname, String lastname, String email, String password) {

        User newUser = new User(null, firstname, lastname, email, password);
        userRepository.save(newUser);
    }

    @Override
    public void updatePassword(User user, String newPassword) {

        user.setPassword(newPassword);
        userRepository.save(user);
    }
}