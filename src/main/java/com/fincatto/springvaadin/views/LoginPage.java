package com.fincatto.springvaadin.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

@Route(value = "login")
@HtmlImport("styles/shared-styles.html")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class LoginPage extends VerticalLayout implements RouterLayout {

    private String email;

    public String getEmail() {
        return email;
    }

    public LoginPage setEmail(String email) {
        this.email = email;
        return this;
    }

    public LoginPage() {
        final Binder<Login> binder = new Binder<>();

        final H2 header = new H2("Login");
        header.addClassName("sem_margem");

        final TextField tfEmail = new TextField();
        //tfEmail.addBlurListener(vcl -> tfEmail.setInvalid(!vcl.getSource().getValue().contains("@")));
        //tfEmail.addKeyPressListener(l -> tfEmail.setInvalid(!tfEmail.getValue().contains("@")));
        tfEmail.setPlaceholder("Informe seu e-mail");
        tfEmail.setLabel("Usuario");
        tfEmail.setRequired(true);
        tfEmail.focus();
        binder.forField(tfEmail).withValidator(new EmailValidator("Isso não parece ser um email válido")).bind(Login::getEmail, Login::setEmail);

        final PasswordField tfSenha = new PasswordField();
        binder.forField(tfSenha).withValidator(new StringLengthValidator("A senha deve ter no minimo 8 caracteres", 8, 99)).bind(Login::getSenha, Login::setSenha);
        //tfSenha.addKeyPressListener(l -> tfSenha.setInvalid(tfSenha.getValue().length() < 8));
        tfSenha.setPlaceholder("Informe sua senha");
        tfSenha.setLabel("Senha");
        tfSenha.setRequired(true);

        final FormLayout formLayout = new FormLayout();
        formLayout.addClassName("sem_margem");
        formLayout.add(tfEmail, tfSenha);
        //formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1), new FormLayout.ResponsiveStep("600px", 2), new FormLayout.ResponsiveStep("1000px", 3), new FormLayout.ResponsiveStep("1400px", 4));

        final Button botaoSalvar = new Button("Entrar", b -> this.getUI().ifPresent(ui -> ui.navigate(HomePage.class)));
        final Button botaoCancelar = new Button("Cancelar", b -> Notification.show("Cancelado!"));
        botaoCancelar.setClassName("botao_cancelar");

        final HorizontalLayout horizontalLayout = new HorizontalLayout(botaoSalvar, botaoCancelar);

        final VerticalLayout verticalLayout = new VerticalLayout(header, formLayout, horizontalLayout);
        verticalLayout.setMargin(false);
        this.add(verticalLayout);
    }

    static class Login {

        private String email, senha;

        String getEmail() {
            return email;
        }

        Login setEmail(String email) {
            this.email = email;
            return this;
        }

        String getSenha() {
            return senha;
        }

        Login setSenha(String senha) {
            this.senha = senha;
            return this;
        }
    }
}

