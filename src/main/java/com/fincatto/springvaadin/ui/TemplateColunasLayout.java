package com.fincatto.springvaadin.ui;

import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;

@HtmlImport("styles/shared-styles.html")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class TemplateColunasLayout extends HorizontalLayout implements RouterLayout {

    public TemplateColunasLayout() {
        final VerticalLayout menuArea = new VerticalLayout();
        menuArea.add(new Label("Menu"), new RouterLink("Submenu 1", HomePage.class), new RouterLink("Submenu 2", OtherPage.class));
        menuArea.addClassName("menu");
        menuArea.setWidth("200px");
        //menuArea.setSpacing(false);
        //menuArea.setPadding(false);
        super.add(menuArea);
    }
}
