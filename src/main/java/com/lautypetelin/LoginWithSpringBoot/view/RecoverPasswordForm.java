package com.lautypetelin.LoginWithSpringBoot.view;

import com.lautypetelin.LoginWithSpringBoot.models.PasswordGenerator;
import com.lautypetelin.LoginWithSpringBoot.models.User;
import com.lautypetelin.LoginWithSpringBoot.services.IEmailService;
import com.lautypetelin.LoginWithSpringBoot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class RecoverPasswordForm extends JFrame{

    //Attributes
    private JPanel recoverPasswordPanel;
    private JTextField txtEmail;
    private JButton btnRecovery;
    private JButton btnCancel;
    private UserService userService;

    @Autowired
    private IEmailService emailService;

    @Autowired
    //Constructor
    public RecoverPasswordForm(UserService userService) {
        this.userService = userService;
        initForm();
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnRecovery.addActionListener(e -> sendEmail());
    }

    private void initForm(){

        setTitle("Recupera tu cuenta de usuario");
        setContentPane(recoverPasswordPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        setVisible(false);
        setMinimumSize(new Dimension(580, 250));
        setLocationRelativeTo(null);
    }

    private void sendEmail(){

        String asunto = "Recuperación de cuenta";
        String to = txtEmail.getText();

        String newPassword = PasswordGenerator.generatePassword(12); //Este metodo genera una contraseña de 12 caracteres que cumple con los requisitos.

        String body = "Hola, tu nueva contraseña es: " + newPassword;

        emailService.sendEmail(asunto,to, body);

        resetPassword(to, newPassword);

    }

    private void resetPassword(String email, String newPassword){

        User userFind = null;

        for(User user: userService.getUsers()){
            if(user.getEmail().equals(email)){
                userFind = user;
            }
        }

        if(userFind != null){
            userService.updatePassword(userFind, newPassword);
            JOptionPane.showMessageDialog(this, "Tu nueva contraseña es: " + newPassword);
        }else{
            JOptionPane.showMessageDialog(this, "No existe usuario con el email proporcionado.");
        }
    }
}
