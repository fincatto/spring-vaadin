package com.fincatto.springvaadin.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

class PersonForm extends VerticalLayout {
    PersonForm(final Dialog dialog) {
        final H3 header = new H3("Pessoa");

        final TextField titleField = new TextField();
        titleField.setLabel("Title");
        titleField.setPlaceholder("Sir");
        titleField.setAutofocus(true);

        final TextField firstNameField = new TextField();
        firstNameField.setLabel("First name");
        firstNameField.setPlaceholder("John");

        final TextField lastNameField = new TextField();
        lastNameField.setLabel("Last name");
        lastNameField.setPlaceholder("Doe");

        final FormLayout formLayout = new FormLayout();
        formLayout.add(titleField, firstNameField, lastNameField);
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1), new FormLayout.ResponsiveStep("21em", 2), new FormLayout.ResponsiveStep("22em", 3));

        final Button botaoSalvar = new Button("Salvar");
        botaoSalvar.getElement().getThemeList().add("primary");
        botaoSalvar.addClickListener(e -> dialog.close());

        final Button botaoCancelar = new Button("Cancelar");
        botaoCancelar.getElement().getThemeList().add("error");
        botaoCancelar.addClickListener(e -> dialog.close());

        final HorizontalLayout botoesLayout = new HorizontalLayout(botaoSalvar, botaoCancelar);
        this.add(header, formLayout, botoesLayout);
    }
}
