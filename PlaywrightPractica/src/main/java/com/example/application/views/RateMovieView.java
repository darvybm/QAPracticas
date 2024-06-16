package com.example.application.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("rate-movie")
public class RateMovieView extends VerticalLayout {

    public RateMovieView() {
        // Título de la vista
        H1 title = new H1("Calificar Película");

        // Crear campos para nombre de la película y autor
        TextField movieNameField = new TextField("Nombre de la Película");
        TextField authorField = new TextField("Autor");

        // Crear grupo de botones de radio para la calificación de estrellas
        RadioButtonGroup<Integer> ratingGroup = new RadioButtonGroup<>();
        ratingGroup.setLabel("Calificación de Estrellas");
        ratingGroup.setItems(1, 2, 3, 4, 5);

        // Crear span para mostrar mensajes de estado
        Span statusSpan = new Span();
        statusSpan.setId("rate-movie-status");

        // Botón de envío
        Button submitButton = new Button("Enviar", event -> {
            String movieName = movieNameField.getValue();
            String author = authorField.getValue();
            Integer rating = ratingGroup.getValue();

            // Validación de campos
            if (!movieName.isEmpty() && !author.isEmpty() && rating != null) {
                // Lógica para manejar la calificación de la película
                statusSpan.setText("Película calificada exitosamente");
                // Limpiar campos después de enviar
                movieNameField.clear();
                authorField.clear();
                ratingGroup.clear();
            } else {
                statusSpan.setText("Por favor, completa todos los campos");
            }
        });

        // Añadir título, campos, grupo de botones de radio, span y botón al layout
        add(title, movieNameField, authorField, ratingGroup, submitButton, statusSpan);
    }
}
