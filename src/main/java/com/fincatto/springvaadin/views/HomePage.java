package com.fincatto.springvaadin.views;

import com.fincatto.springvaadin.layouts.TemplateColunasLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@PageTitle("Admin")
@Route(value = "", layout = TemplateColunasLayout.class)
public class HomePage extends Composite<VerticalLayout> {

    public HomePage() {
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

        final FormLayout formLayout = new FormLayout();
        formLayout.add(titleField, firstNameField, lastNameField);
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1), new FormLayout.ResponsiveStep("21em", 2), new FormLayout.ResponsiveStep("22em", 3));

        final HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(new RouterLink("Listar", UsersPage.class));

        this.getContent().add(new VerticalLayout(formLayout, horizontalLayout));
    }
}
