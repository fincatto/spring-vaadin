package com.fincatto.springvaadin.views;

import com.fincatto.springvaadin.components.WMXVerticalLayoutComposite;
import com.fincatto.springvaadin.components.WMXHeader;
import com.fincatto.springvaadin.layouts.TemplatePrincipalLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("I18n")
@Route(value = "i18n", layout = TemplatePrincipalLayout.class)
public class InternationalizationPage extends WMXVerticalLayoutComposite {

    public InternationalizationPage() {
        final WMXHeader header = new WMXHeader("I18n");
        final Label label = new Label("Tem que implementar isso aqui");
        this.getContent().add(header, label);
    }
}
