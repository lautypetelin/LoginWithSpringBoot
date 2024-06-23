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
public class RegistrationForm extends JFrame {

    //Attributes
    private JTextField txtName;
    private JTextField txtApellido;
    private JTextField txtEmail;
    private JTextField txtPassword;
    private JTextField txtConfirmPassword;
    private JButton btnRegister;
    private JButton btnCancel;
    private JPanel registerPanel;
    private UserService userService;

    @Autowired
    //Constructor
    public RegistrationForm(UserService userService) {
        this.userService = userService;
        initForm();
        btnRegister.addActionListener(e -> registerNewUser());
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void initForm() {
            setTitle("Crear una nueva cuenta");
            setContentPane(registerPanel);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);
            setUndecorated(true);
            setVisible(false);
            setMinimumSize(new Dimension(850, 475));
            setLocationRelativeTo(null);
    }

    private void registerNewUser () {

        if(validateFields()){
            return;
        }

        String firstname = txtName.getText();
        String lastname = txtApellido.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();

        //Validar si ya existe un usuario con el mismo email.
        for(User user: userService.getUsers()){
            if(user.getEmail().equals(email)){
                showMessage("Ya existe un usuario con este correo electrónico.");
                return;
            }
        }

        String confirmPassword = txtConfirmPassword.getText();
        if(validatePassword(password ,confirmPassword)){
            return;
        }

        userService.register(firstname, lastname, email, password);
        showMessage("Usuario registrado correctamente.");
    }

    private boolean validateFields(){

        if(txtName.getText().isEmpty()){
            showMessage("Debe completar el campo 'Nombre'");
            return true;
        }

        if(txtApellido.getText().isEmpty()){
            showMessage("Debe completar el campo 'Apellido'");
            return true;
        }

        if(txtEmail.getText().isEmpty()){
            showMessage("Debe completar el campo 'Email'");
            return true;
        }

        if(txtPassword.getText().isEmpty()){
            showMessage("Debe completar el campo 'Password'");
            return true;
        }

        if(txtConfirmPassword.getText().isEmpty()){
            showMessage("Debe completar el campo 'Confirm Password'");
            return true;
        }

        if(!txtEmail.getText().contains("@")){
            showMessage("Usuario/contraseña incorrectos.\n\n" +
                    "El campo 'email' debe incluir el caracter @.\n" +
                    "El campo 'password' debe incluir al menos una letra mayúscula.\n" +
                    "El campo 'password' debe incluir al menos una letra minúscula.\n" +
                    "El campo 'password' debe incluir al menos un número.\n" +
                    "El campo 'password' debe incluir al menos un símbolo.\n" +
                    "El campo 'password' debe incluir al menos 8 caracteres.");
            return true;
        }

        return false;
    }

    private boolean validatePassword(String password, String confirmPassword){

        if(!password.equals(confirmPassword)){
            showMessage("Las contraseñas no coinciden.");
            return true;
        }

        boolean tieneMayuscula = Pattern.compile("[A-Z]").matcher(password).find();
        boolean tieneMinuscula = Pattern.compile("[a-z]").matcher(password).find();
        boolean tieneNumero = Pattern.compile("[0-9]").matcher(password).find();
        boolean tieneSimbolo = Pattern.compile("[^A-Za-z0-9]").matcher(password).find();
        boolean esSuficientementeLarga = password.length() >= 8;

        if (tieneMayuscula && tieneMinuscula && tieneNumero && tieneSimbolo && esSuficientementeLarga) {
            return false;
        } else {
            showMessage("Usuario/contraseña incorrectos.\n\n" +
                        "El campo 'email' debe incluir el caracter @.\n" +
                        "El campo 'password' debe incluir al menos una letra mayúscula.\n" +
                        "El campo 'password' debe incluir al menos una letra minúscula.\n" +
                        "El campo 'password' debe incluir al menos un número.\n" +
                        "El campo 'password' debe incluir al menos un símbolo.\n" +
                        "El campo 'password' debe incluir al menos 8 caracteres.");
            return true;
        }
    }

    private void showMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }
}