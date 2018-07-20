package com.fincatto.springvaadin.views;

import com.fincatto.springvaadin.layouts.TemplateColunasLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Admin")
@Route(value = "", layout = TemplateColunasLayout.class)
public class HomePage extends Composite<VerticalLayout> {

    public HomePage() {
        final H2 header = new H2("Registration");
        header.addClassName("sem_margem");

        final TextField titleField = new TextField();
        titleField.setLabel("Title");
        titleField.setPlaceholder("Sir");
        titleField.focus();

        final TextField firstNameField = new TextField();
        firstNameField.setLabel("First name");
        firstNameField.setPlaceholder("John");

        final TextField lastNameField = new TextField();
        lastNameField.setLabel("Last name");
        lastNameField.setPlaceholder("Doe");

        final TextField phoneField = new TextField();
        phoneField.setLabel("Phone");
        phoneField.setPrefixComponent(VaadinIcon.PHONE.create());

        final TextField emailField = new TextField();
        emailField.setLabel("E-mail");
        emailField.setPrefixComponent(VaadinIcon.ENVELOPE.create());

        final TextField twitterField = new TextField();
        twitterField.setLabel("Twitter");
        twitterField.setPrefixComponent(VaadinIcon.TWITTER.create());

        final FormLayout formLayout = new FormLayout();
        formLayout.addClassName("sem_margem");

        formLayout.add(titleField, firstNameField, lastNameField, phoneField, emailField, twitterField);
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1), new FormLayout.ResponsiveStep("21em", 2), new FormLayout.ResponsiveStep("22em", 3));

        final Button botaoSalvar = new Button("Salvar", b -> Notification.show("Salvo com sucesso!"));
        final Button botaoCancelar = new Button("Cancelar", b -> Notification.show("Cancelado!"));
        botaoCancelar.setClassName("botao_cancelar");

        final HorizontalLayout horizontalLayout = new HorizontalLayout(botaoSalvar, botaoCancelar);
        //horizontalLayout.setMargin(true);

        final VerticalLayout verticalLayout = new VerticalLayout(header, formLayout, horizontalLayout);
        verticalLayout.setMargin(false);

        this.getContent().add(verticalLayout);
    }
}
