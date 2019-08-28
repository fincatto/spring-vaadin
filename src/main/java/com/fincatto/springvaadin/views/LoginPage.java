package com.fincatto.springvaadin.views;

import com.fincatto.springvaadin.Loggable;
import com.fincatto.springvaadin.layouts.TemplateSimplesLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.Set;
import java.util.stream.Collectors;

@Route(value = "login", layout = TemplateSimplesLayout.class)
public class LoginPage extends Div implements RouterLayout, Loggable {

    @Autowired
    public LoginPage(final UserDetailsManager manager) {
        final LoginForm loginForm = new LoginForm();
        loginForm.setForgotPasswordButtonVisible(false);
        loginForm.addLoginListener(e -> {
            final boolean autenticado = autenticarUsuario(manager, e.getUsername(), e.getPassword());
            if (autenticado) {
                this.getUI().ifPresent(ui -> ui.navigate(HomePage.class));
            } else {
                loginForm.setError(true);
            }
        });
        this.add(loginForm);
    }

    private boolean autenticarUsuario(final UserDetailsManager manager, final String email, final String senha) {
        SecurityContextHolder.getContext().setAuthentication(null);
        if (Strings.isNotBlank(email)) {
            if (manager.userExists(email)) {
                final UserDetails userDetails = manager.loadUserByUsername(email);
                if (!userDetails.getPassword().equals(senha)) {
                    getLogger().warn("Senha incorreta para usuario '{}'!", email);
                    return false;
                }
                if (!userDetails.isEnabled()) {
                    getLogger().warn("Conta desabilitada para usuario: '{}'!", email);
                    return false;
                }
                if (!userDetails.isAccountNonExpired()) {
                    getLogger().warn("Conta expirada para usuario: '{}'!", email);
                    return false;
                }
                if (!userDetails.isAccountNonLocked()) {
                    getLogger().warn("Conta travada para usuario: '{}'!", email);
                    return false;
                }
                if (!userDetails.isCredentialsNonExpired()) {
                    getLogger().warn("Credencial expirada para usuario: '{}'!", email);
                    return false;
                }

                //se chegou aqui eh porque esta tudo certo
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities()));
                final Set<String> roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toUnmodifiableSet());
                getLogger().warn("{}: {}", userDetails.getUsername(), roles.toString());
                return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
            } else {
                getLogger().warn("Usuario inexistente para email '{}'!", email);
                return false;
            }
        }
        return false;
    }
}

