package com.fincatto.springvaadin.views;

import com.fincatto.springvaadin.components.WMXForm;
import com.fincatto.springvaadin.layouts.TemplatePrincipalLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Form Comp")
@Route(value = "form-comp", layout = TemplatePrincipalLayout.class)
public class FormularioComponentePage extends Composite<Div> {
    
    public FormularioComponentePage() {
        final TextField firstNameField = new TextField();
        firstNameField.setLabel("Nome");
        firstNameField.setPlaceholder("Diego");
        firstNameField.focus();
        
        final TextField lastNameField = new TextField();
        lastNameField.setLabel("Sobrenome");
        lastNameField.setPlaceholder("Fincatto");
        
        final TextField phoneField = new TextField();
        phoneField.setLabel("Telefone");
        phoneField.setPrefixComponent(VaadinIcon.PHONE.create());
        
        final TextField emailField = new TextField();
        emailField.setLabel("E-mail");
        emailField.setPrefixComponent(VaadinIcon.ENVELOPE.create());
        
        final WMXForm form = new WMXForm("Formulario automatico");
        form.addFormComponent(firstNameField, lastNameField, phoneField, emailField);
        form.addBotaoSalvar(b -> Notification.show("Salvo com sucesso!"));
        form.addBotaoCancelar(b -> Notification.show("Cancelado!"));
        
        this.getContent().add(form);
        this.getContent().setSizeFull();
    }
}
