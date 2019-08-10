package com.fincatto.springvaadin.components;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * Classe que representa um formulario qualquer no sistema. <br>
 * Padroniza componentes e estilos no sistema inteiro.
 */
public class WMXForm extends Composite<VerticalLayout> {
    
    public WMXForm(String titulo) {
        final HorizontalLayout header = new HorizontalLayout(new H3(titulo));
        header.setWidthFull();
        header.setMargin(false);
        header.setSpacing(false);
        
        final FormLayout formLayout = new FormLayout();
        //formLayout.add(titleField, firstNameField, lastNameField, phoneField, emailField, twitterField);
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1), new FormLayout.ResponsiveStep("600px", 2), new FormLayout.ResponsiveStep("1000px", 3), new FormLayout.ResponsiveStep("1400px", 4));
        
        final Button botaoSalvar = new Button("Salvar", b -> Notification.show("Salvo com sucesso!"));
        botaoSalvar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        final Button botaoCancelar = new Button("Cancelar", b -> Notification.show("Cancelado!"));
        botaoCancelar.addThemeVariants(ButtonVariant.LUMO_ERROR);
        
        final HorizontalLayout horizontalLayout = new HorizontalLayout(botaoCancelar, botaoSalvar);
        
        this.getContent().setMargin(false);
        this.getContent().setSpacing(false);
        this.getContent().add(header, formLayout, horizontalLayout);
    }
}
