package com.fincatto.springvaadin.views;

import com.fincatto.springvaadin.Loggable;
import com.fincatto.springvaadin.layouts.TemplateSimplesLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

@Route(value = "login", layout = TemplateSimplesLayout.class)
public class LoginPage extends Composite<VerticalLayout> implements RouterLayout, Loggable {

    @Autowired
    public LoginPage(final UserDetailsManager manager) {
        final H2 header = new H2("Login");
        header.addClassName("sem_margem");

        final TextField tfEmail = new TextField();
        tfEmail.setPlaceholder("Informe seu e-mail");
        tfEmail.setLabel("Usuario");
        tfEmail.setRequired(true);
        tfEmail.focus();

        final PasswordField tfSenha = new PasswordField();
        tfSenha.setPlaceholder("Informe sua senha");
        tfSenha.setLabel("Senha");
        tfSenha.setRequired(true);

        final FormLayout formLayout = new FormLayout();
        formLayout.addClassName("sem_margem");
        formLayout.add(tfEmail, tfSenha);

        final Button botaoSalvar = new Button("Entrar", b -> this.getUI().ifPresent(ui -> ui.navigate(autenticarUsuario(manager, tfEmail.getValue(), tfSenha.getValue()) ? HomePage.class : LoginPage.class)));
        final Button botaoCancelar = new Button("Cancelar", b -> this.getUI().ifPresent(ui -> ui.navigate("accessDenied")));
        botaoCancelar.setClassName("botao_cancelar");

        final HorizontalLayout horizontalLayout = new HorizontalLayout(botaoSalvar, botaoCancelar);

        final VerticalLayout verticalLayout = new VerticalLayout(header, formLayout, horizontalLayout);
        verticalLayout.setMargin(false);
        this.getContent().add(verticalLayout);
    }

    private boolean autenticarUsuario(UserDetailsManager manager, String email, String senha) {
        try {
            if (Strings.isNotBlank(email)) {
                final UserDetails userDetails = manager.loadUserByUsername(email);
                if (userDetails != null && userDetails.getUsername().equalsIgnoreCase(email) && userDetails.getPassword().equals(senha)) {
                    final Authentication auth = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    return true;
                }
            }
        } catch (UsernameNotFoundException e) {
            getLogger().debug("Usuario nao encontrado: {}", email);
        } catch (Exception e) {
            getLogger().debug("Erro desconhecido ao logar usuario: {}", email);
            e.printStackTrace();
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return false;
    }
}

