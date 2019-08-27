package com.fincatto.springvaadin.views;

import com.fincatto.springvaadin.components.WMXHeader;
import com.fincatto.springvaadin.layouts.TemplatePrincipalLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Home")
@Route(value = "", layout = TemplatePrincipalLayout.class)
public class HomePage extends Composite<VerticalLayout> {

    public HomePage() {
        final WMXHeader header = new WMXHeader("Home");
        final Label label = new Label("Bem-vindo ao sistema!");
        this.getContent().add(header, label);
        this.getContent().setSizeFull();
        this.getContent().setMargin(false);
        this.getContent().setSpacing(false);
    }
}
