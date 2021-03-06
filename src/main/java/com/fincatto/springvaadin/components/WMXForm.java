package com.fincatto.springvaadin.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * Classe que representa um formulario qualquer no sistema. <br>
 * Padroniza componentes e estilos no sistema inteiro.
 */
public class WMXForm extends Composite<VerticalLayout> {
    
    private final FormLayout formLayout;
    private final HorizontalLayout horizontalLayoutAcoes;
    private final WMXHeader header;

    public WMXForm(String titulo) {
        this.header = new WMXHeader(titulo);

        this.formLayout = new FormLayout();
        this.formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1), new FormLayout.ResponsiveStep("600px", 2), new FormLayout.ResponsiveStep("1000px", 3), new FormLayout.ResponsiveStep("1400px", 4));

        this.horizontalLayoutAcoes = new HorizontalLayout();

        this.getContent().add(header, formLayout, horizontalLayoutAcoes);
        this.getContent().setMargin(false);
        this.getContent().setSizeFull();
    }

    public WMXForm addHeaderComponent(final Component... components) {
        header.add(components);
        return this;
    }

    public WMXForm addFormComponent(final Component... components) {
        formLayout.add(components);
        return this;
    }
    
    public WMXForm addFormItem(final Component component, String label) {
        formLayout.addFormItem(component, label);
        return this;
    }
    
    public WMXForm addFormItem(final Component component, Component label) {
        formLayout.addFormItem(component, label);
        return this;
    }
    
    public WMXForm addBotao(final Button botao) {
        horizontalLayoutAcoes.add(botao);
        return this;
    }
    
    public WMXForm addBotaoCancelar(final ComponentEventListener<ClickEvent<Button>> clickListener) {
        final Button button = new Button("Cancelar", clickListener);
        button.addThemeVariants(ButtonVariant.LUMO_ERROR);
        return this.addBotao(button);
    }
    
    public WMXForm addBotaoSalvar(final ComponentEventListener<ClickEvent<Button>> clickListener) {
        final Button button = new Button("Salvar", clickListener);
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        return this.addBotao(button);
    }
}
