package com.fincatto.springvaadin;

import com.fincatto.springvaadin.views.AccessDeniedPage;
import com.fincatto.springvaadin.views.LoginPage;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SpringVaadinSecurityListener implements VaadinServiceInitListener {

    @Override
    public void serviceInit(final ServiceInitEvent event) {
        event.getSource().addUIInitListener(uie -> uie.getUI().addBeforeEnterListener(this::beforeEnter));
    }

    private void beforeEnter(final BeforeEnterEvent event) {
        if (!isAccessGranted(event.getNavigationTarget())) {
            if (isUserLoggedIn()) {
                event.rerouteTo(AccessDeniedPage.class);
            } else {
                event.rerouteTo(LoginPage.class);
            }
        }
    }

    private static boolean isAccessGranted(final Class<?> securedClass) {
        // sempre permito o login
        if (LoginPage.class.equals(securedClass)) {
            return true;
        }

        // para as demais paginas, exijo que o usuario esteja logado
        if (!isUserLoggedIn()) {
            return false;
        }


        // se nao tiver anotacao de seguranca, permito
        final Secured secured = AnnotationUtils.findAnnotation(securedClass, Secured.class);
        if (secured == null) {
            return true;
        }

        // lookup needed role in user roles
        return isAccessGranted(secured.value());
    }

    public static boolean isAccessGranted(final String... roles) {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(Arrays.asList(roles)::contains);
    }
    
    public static boolean isUserLoggedIn() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null //nao pode ser nulo
                && !(authentication instanceof AnonymousAuthenticationToken) //nao pode ser anonimo
                && authentication.isAuthenticated(); //tem que estar autenticado
    }
}