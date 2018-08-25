package com.fincatto.springvaadin.views;

import com.fincatto.springvaadin.Loggable;
import com.fincatto.springvaadin.layouts.TemplateColunasLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@PageTitle("Clients")
@Route(value = "client", layout = TemplateColunasLayout.class)
public class ClientPage extends Composite<Div> implements Loggable {

    public ClientPage() {
        final Map<Tab, Component> tabsToPages = new HashMap<>();

        final Tab tabUser = new Tab("Diego Fincatto");
        //tabUser.setEnabled(false);
        final Div divUser = new Div();
        divUser.setClassName("user_div");
        divUser.setText("Usuario");
        tabsToPages.put(tabUser, divUser);

        final Tab tabPedidos = new Tab("Pedidos");
        final Div divPedidos = new Div();
        divPedidos.setClassName("user_div");
        divPedidos.setText("Pedidos");
        divPedidos.setVisible(false);
        tabsToPages.put(tabPedidos, divPedidos);

        final Tab tabNotas = new Tab("Notas");
        final Div divNotas = new Div();
        divNotas.setClassName("user_div");
        divNotas.setText("Notas");
        divNotas.setVisible(false);
        tabsToPages.put(tabNotas, divNotas);

        final Div pages = new Div(divUser, divPedidos, divNotas);
        pages.setClassName("user_divs");

        final Set<Component> pagesShown = Stream.of(divUser).collect(Collectors.toSet());

        final Tabs tabs = new Tabs(tabUser, tabPedidos, tabNotas, new Tab("Cobranças"), new Tab("Históricos"), new Tab("Tab six"), new Tab("Tab seven"), new Tab("Tab eight"), new Tab("Tab nine"), new Tab("Tab ten"), new Tab("Tab eleven"), new Tab("Tab twelve"), new Tab("Tab thirteen"), new Tab("Tab fourteen"), new Tab("Tab fifteen"));
        tabs.setWidth("100%");
        tabs.addClassName("user_tabs");

        tabs.addSelectedChangeListener(event -> {
            Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
            if (selectedPage != null) {
                pagesShown.forEach(page -> page.setVisible(false));
                pagesShown.clear();
                selectedPage.setVisible(true);
                pagesShown.add(selectedPage);
            }
        });

        this.getContent().add(tabs, pages);
        this.getContent().setSizeFull();
    }
}
