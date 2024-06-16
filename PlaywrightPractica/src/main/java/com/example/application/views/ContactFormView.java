package com.example.application.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.regex.Pattern;

@Route("contact")
public class ContactFormView extends VerticalLayout {

    public ContactFormView() {
        // Título del formulario de contacto
        H1 title = new H1("Contacto");
        title.setId("contact-form-title");

        // Crear campos del formulario de contacto
        TextField nameField = new TextField("Nombre");
        nameField.setId("contact-form-name");

        EmailField emailField = new EmailField("Correo Electrónico");
        emailField.setId("contact-form-email");

        TextArea messageField = new TextArea("Mensaje");
        messageField.setId("contact-form-message");

        // Crear span para mostrar mensajes de estado
        Span statusSpan = new Span();
        statusSpan.setId("contact-form-status");

        // Botón de envío
        Button submitButton = new Button("Enviar", event -> {
            String name = nameField.getValue();
            String email = emailField.getValue();
            String message = messageField.getValue();

            // Validación de campos
            if (!name.isEmpty() && isValidEmail(email) && !message.isEmpty()) {
                // Suponiendo que se envía el mensaje exitosamente
                statusSpan.setText("Mensaje enviado exitosamente");
                // Limpiar campos después de enviar
                nameField.clear();
                emailField.clear();
                messageField.clear();
            } else if (!isValidEmail(email)) {
                statusSpan.setText("Por favor, ingresa un correo electrónico válido");
            } else {
                statusSpan.setText("Por favor, completa todos los campos");
            }
        });
        submitButton.setId("contact-form-submit");

        // Añadir título, campos, span y botón al layout
        add(title, nameField, emailField, messageField, submitButton, statusSpan);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}
