package com.lautypetelin.LoginWithSpringBoot.services;

import com.lautypetelin.LoginWithSpringBoot.models.User;

import java.util.List;

public interface IUserService {

    List<User> getUsers();
    void register(String firstname, String lastname, String email, String password);
    void updatePassword(User user, String newPassword);
}