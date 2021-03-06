package com.fincatto.springvaadin.views;

import com.fincatto.springvaadin.components.WMXVerticalLayoutComposite;
import com.fincatto.springvaadin.components.WMXHeader;
import com.fincatto.springvaadin.layouts.TemplatePrincipalLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Home")
@Route(value = "", layout = TemplatePrincipalLayout.class)
public class HomePage extends WMXVerticalLayoutComposite {

    public HomePage() {
        final WMXHeader header = new WMXHeader("Home");
        final Label label = new Label("Bem-vindo ao sistema!");
        this.getContent().add(header, label);
    }
}
