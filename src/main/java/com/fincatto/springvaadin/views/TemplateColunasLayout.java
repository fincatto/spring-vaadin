package com.fincatto.springvaadin.views;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.RouterLayout;

import java.util.Arrays;

@HtmlImport("styles/shared-styles.html")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class TemplateColunasLayout extends VerticalLayout implements RouterLayout {

    public TemplateColunasLayout() {
        this.setMargin(false);
        this.setSpacing(false);
        this.setPadding(false);
        this.setSizeFull();

        final ComboBox<String> comboBox = new ComboBox<>(null, Arrays.asList("Google Chrome", "Mozilla Firefox", "Opera", "Apple Safari", "Microsoft Edge"));

        final Icon menuIcon = VaadinIcon.MENU.create();

        final HorizontalLayout header = new HorizontalLayout();
        header.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        header.add(menuIcon);
        header.add(new Label("Admin"));
        header.add(comboBox);
        //header.add(new TextField(null, "Busca..."));
        header.addClassName("header");
        header.setWidth("100%");
        header.setHeight("50px");
        header.setPadding(true);

        this.add(header);

        //        final Label menuHeader = new Label("Menu");
        //        final RouterLink menuLink1 = new RouterLink("Submenu 1", HomePage.class);
        //        final RouterLink menuLink2 = new RouterLink("Submenu 2", OtherPage.class);
        //
        //        final VerticalLayout menuArea = new VerticalLayout();
        //        menuArea.add(menuHeader, menuLink1, menuLink2);
        //        menuArea.addClassName("menu");
        //        menuArea.setWidth("200px");
        //        menuArea.setSpacing(false);
        //        menuArea.setPadding(false);
        //        super.add(menuArea);
    }
}
