package com.fincatto.springvaadin.views;

import com.fincatto.springvaadin.layouts.TemplateMenuNativoLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.SpringVaadinSession;

import java.time.LocalDateTime;

@PageTitle("Session")
@Route(value = "session", layout = TemplateMenuNativoLayout.class)
public class SessionPage extends Composite<VerticalLayout> {
    
    private static final String SESSION_ATTRIB = "nome";
    
    public SessionPage() {
        final Label label = new Label(String.valueOf(SpringVaadinSession.getCurrent().getAttribute(SESSION_ATTRIB)));
        
        final FormLayout flForm = new FormLayout();
        flForm.setWidth("100%");
        flForm.add(label);
        
        final Button botaoSalvar = new Button("Setar dado na sessao", b -> {
            SpringVaadinSession.getCurrent().setAttribute(SESSION_ATTRIB, String.valueOf(LocalDateTime.now()));
        });
        botaoSalvar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        final Button botaoCancelar = new Button("Ler dado na sessao", b -> {
            label.setText(String.valueOf(SpringVaadinSession.getCurrent().getAttribute(SESSION_ATTRIB)));
        });
        
        final HorizontalLayout hlBotoes = new HorizontalLayout(botaoSalvar, botaoCancelar);
        final VerticalLayout vlConteudo = new VerticalLayout(flForm, hlBotoes);
        this.getContent().add(vlConteudo);
    }
}
