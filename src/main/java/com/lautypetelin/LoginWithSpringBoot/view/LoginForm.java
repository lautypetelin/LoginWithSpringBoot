package com.lautypetelin.LoginWithSpringBoot.view;

import com.lautypetelin.LoginWithSpringBoot.models.User;
import com.lautypetelin.LoginWithSpringBoot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

@Component
public class LoginForm extends JFrame{

    //Attributes
    private JTextField txtEmail;
    private JTextField txtPassword;
    private JButton btnLogin;
    private JButton btnForget;
    private JButton btnCancel;
    private JPanel loginPanel;
    private UserService userService;

    @Autowired
    private RecoverPasswordForm recoverPasswordView;

    @Autowired
    //Constructor
    public LoginForm(UserService userService) {
        this.userService = userService;
        initForm();
        btnLogin.addActionListener(e -> login());

        //OLVIDE MI CLAVE
        btnForget.addActionListener(e -> openRecoverPassword());

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void initForm(){
        setTitle("Iniciar sesión");
        setContentPane(loginPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        setVisible(false);
        setMinimumSize(new Dimension(600, 475));
        setLocationRelativeTo(null);
    }

    private void login(){

        if(validateFields()){
            return;
        }

        String email = txtEmail.getText();
        String password = txtPassword.getText();

        for(User user: userService.getUsers()){
            if(user.getEmail().equals(email) && user.getPassword().equals(password)){
                showMessage("Inicio de sesión correcto.");
                return;
            }
        }

        showMessage("No existe cuenta con los datos proporcionados.");
    }

    private void openRecoverPassword(){
        recoverPasswordView.setVisible(true);
    }

    private boolean validateFields(){

        //Si los campos estan vacíos.
        if(txtEmail.getText().isEmpty()){
            showMessage("Debe completar el campo 'Email'");
            return true;
        }

        if(txtPassword.getText().isEmpty()){
            showMessage("Debe completar el campo 'Password'");
            return true;
        }

        //Si los campos no cumplen con los requisitos.
        if(!txtEmail.getText().contains("@") || !validatePassword(txtPassword.getText())){
            showMessage("Usuario/contraseña incorrectos");
            return true;
        }

        return false;
    }


    private boolean validatePassword(String password){

        boolean tieneMayuscula = Pattern.compile("[A-Z]").matcher(password).find();
        boolean tieneMinuscula = Pattern.compile("[a-z]").matcher(password).find();
        boolean tieneNumero = Pattern.compile("[0-9]").matcher(password).find();
        boolean tieneSimbolo = Pattern.compile("[^A-Za-z0-9]").matcher(password).find();
        boolean esSuficientementeLarga = password.length() >= 8;

        if (tieneMayuscula && tieneMinuscula && tieneNumero && tieneSimbolo && esSuficientementeLarga) {
            return true;
        } else {
            return false;
        }
    }

    private void showMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }
}