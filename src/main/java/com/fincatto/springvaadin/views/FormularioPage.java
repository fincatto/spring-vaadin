package com.fincatto.springvaadin.views;

import com.fincatto.springvaadin.components.WMXVerticalLayoutComposite;
import com.fincatto.springvaadin.components.WMXHeader;
import com.fincatto.springvaadin.layouts.TemplatePrincipalLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Form")
@Route(value = "form", layout = TemplatePrincipalLayout.class)
public class FormularioPage extends WMXVerticalLayoutComposite {
    
    public FormularioPage() {
        final WMXHeader header = new WMXHeader("Formulário");

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
        formLayout.add(titleField, firstNameField, lastNameField, phoneField, emailField, twitterField);
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1), new FormLayout.ResponsiveStep("600px", 2), new FormLayout.ResponsiveStep("1000px", 3), new FormLayout.ResponsiveStep("1400px", 4));
        
        final Button botaoSalvar = new Button("Salvar", b -> Notification.show("Salvo com sucesso!"));
        botaoSalvar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        final Button botaoCancelar = new Button("Cancelar", b -> Notification.show("Cancelado!"));
        botaoCancelar.addThemeVariants(ButtonVariant.LUMO_ERROR);
    
        final HorizontalLayout horizontalLayout = new HorizontalLayout(botaoSalvar, botaoCancelar);
        
        this.getContent().setSizeFull();
        //this.getContent().setMargin(false);
        //this.getContent().setSpacing(false);
        this.getContent().add(header, formLayout, horizontalLayout);
    }
}
