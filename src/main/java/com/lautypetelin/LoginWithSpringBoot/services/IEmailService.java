package com.lautypetelin.LoginWithSpringBoot.services;

public interface IEmailService {

    void sendEmail(String asunto, String to, String body);
}
