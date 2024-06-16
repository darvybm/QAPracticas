package com.example.application.views;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("login")
public class LoginView extends VerticalLayout {

    public LoginView() {
        // Crear el formulario de inicio de sesión
        LoginForm loginForm = new LoginForm();
        loginForm.setId("login-form");

        // Añadir un listener para el evento de inicio de sesión
        loginForm.addLoginListener(e -> {
            String username = e.getUsername();
            String password = e.getPassword();

            // Aquí agregar lógica de autenticación
            if (authenticate(username, password)) {
                loginForm.setEnabled(false);
                Span successMessage = new Span("Inicio de sesión exitoso");
                successMessage.setId("success-message");
                add(successMessage);
            } else {
                loginForm.setError(true);
            }
        });

        // Añadir el formulario al layout
        add(loginForm);
    }

    private boolean authenticate(String username, String password) {
        // Aquí agregar lógica de autenticación
        return true;
    }
}
