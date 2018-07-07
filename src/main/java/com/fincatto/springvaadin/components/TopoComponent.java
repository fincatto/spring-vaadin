package com.fincatto.springvaadin.components;

import com.fincatto.springvaadin.Loggable;
import com.fincatto.springvaadin.views.HomePage;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.RouterLink;

import java.util.Arrays;

public class TopoComponent extends HorizontalLayout implements Loggable {
    public TopoComponent() {
        this.setPadding(true);
        this.setWidth("100%");
        this.setHeight("50px");
        this.addClassName("header");
        this.setDefaultVerticalComponentAlignment(Alignment.CENTER);

        final HorizontalLayout menuEsquerda = new HorizontalLayout();
        menuEsquerda.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        menuEsquerda.setSizeFull();

        final Icon menuIcon = VaadinIcon.MENU.create();
        final RouterLink linkHome = new RouterLink("Admin", HomePage.class);
        linkHome.addClassName("link_home");

        final TextField tfPesquisa = new TextField();
        tfPesquisa.setPlaceholder("Pesquisa...");
        tfPesquisa.addKeyPressListener(Key.ENTER, e -> getLogger().debug("Pesquisando por {}...", tfPesquisa.getValue()));
        menuEsquerda.add(menuIcon, linkHome, tfPesquisa);

        final HorizontalLayout menuDireita = new HorizontalLayout();
        menuDireita.setDefaultVerticalComponentAlignment(Alignment.CENTER);

        final ComboBox<String> comboBox = new ComboBox<>(null, Arrays.asList("Google Chrome", "Mozilla Firefox", "Opera", "Apple Safari", "Microsoft Edge"));
        menuDireita.add(comboBox);

        this.add(menuEsquerda, menuDireita);
    }
}
