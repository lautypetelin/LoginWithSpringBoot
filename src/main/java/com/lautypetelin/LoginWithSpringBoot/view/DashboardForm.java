package com.lautypetelin.LoginWithSpringBoot.view;

import com.lautypetelin.LoginWithSpringBoot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class DashboardForm extends JFrame{

    //Attributes
    private JPanel dashboardPanel;
    private JButton btnRegister;
    private JButton btnLogin;
    private UserService userService;

    //Ventanas
    @Autowired
    private RegistrationForm registerView;

    @Autowired
    private LoginForm loginView;

    @Autowired
    //Constructor
    public DashboardForm(UserService userService) {
        this.userService = userService;
        initForm();
        btnRegister.addActionListener(e -> openRegister());
        btnLogin.addActionListener(e -> openLogin());
    }

    private void initForm(){
        setTitle("¡Bienvenido!");
        setContentPane(dashboardPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setMinimumSize(new Dimension(500, 420));
        setLocationRelativeTo(null);
    }

    private void openRegister(){
        registerView.setVisible(true);
    }

    private void openLogin(){
        loginView.setVisible(true);
    }
}