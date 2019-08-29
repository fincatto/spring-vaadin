package com.fincatto.springvaadin.views;

import com.fincatto.springvaadin.components.WMXVerticalLayoutComposite;
import com.fincatto.springvaadin.components.WMXHeader;
import com.fincatto.springvaadin.layouts.TemplateSimplesLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.Route;

@Route(value = "accessDenied", layout = TemplateSimplesLayout.class)
public class AccessDeniedPage extends WMXVerticalLayoutComposite {

    public AccessDeniedPage() {
        final WMXHeader titulo = new WMXHeader("Acesso negado");
        final Label label = new Label("Chola mais, pq ta pouco");
        this.getContent().add(titulo, label);
    }
}

