package com.fincatto.springvaadin.layouts;

import com.fincatto.springvaadin.components.TopoComponent;
import com.fincatto.springvaadin.repositories.UserRepository;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLayout;
import org.springframework.beans.factory.annotation.Autowired;

@HtmlImport("styles/shared-styles.html")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class TemplateColunasLayout extends VerticalLayout implements RouterLayout {

    @Autowired
    public TemplateColunasLayout(final UserRepository userRepository) {
        this.setMargin(false);
        this.setSpacing(false);
        this.setPadding(false);
        this.setSizeFull();
        this.add(new TopoComponent(userRepository));

        final Tab tabUser = new Tab("Diego Fincatto");
        //tabUser.setEnabled(false);
        final Tabs tabs = new Tabs(tabUser, new Tab("Pedidos"),
                new Tab("Notas"), new Tab("Cobranças"), new Tab("Históricos"),
                new Tab("Tab six"), new Tab("Tab seven"), new Tab("Tab eight"),
                new Tab("Tab nine"), new Tab("Tab ten"), new Tab("Tab eleven"),
                new Tab("Tab twelve"), new Tab("Tab thirteen"),
                new Tab("Tab fourteen"), new Tab("Tab fifteen"));
        tabs.setWidth("100%");
        //tabs.setSelectedIndex(2);
        tabs.addClassName("header_tabs");
        this.add(tabs);
    }
}
