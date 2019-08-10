package com.fincatto.springvaadin.views;

import com.fincatto.springvaadin.layouts.TemplatePrincipalLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Home")
@Route(value = "", layout = TemplatePrincipalLayout.class)
public class HomePage extends Composite<VerticalLayout> {
    
    public HomePage() {
        final HorizontalLayout header = new HorizontalLayout(new H4("Home"));
        header.setWidthFull();
        header.setMargin(false);
        header.setSpacing(false);
    
        final Label label = new Label("Bem-vindo ao sistema!");
    
        this.getContent().setSizeFull();
        this.getContent().setMargin(false);
        this.getContent().setSpacing(false);
        this.getContent().add(header, new Hr(), label);
    }
}
