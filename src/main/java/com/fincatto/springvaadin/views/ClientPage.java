package com.fincatto.springvaadin.views;

import com.fincatto.springvaadin.Loggable;
import com.fincatto.springvaadin.classes.Client;
import com.fincatto.springvaadin.classes.Invoice;
import com.fincatto.springvaadin.layouts.TemplateMenuNativoLayout;
import com.fincatto.springvaadin.repositories.ClientRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@PageTitle("Cliente")
@Route(value = "cliente", layout = TemplateMenuNativoLayout.class)
public class ClientPage extends Composite<Div> implements Loggable {
    
    @Autowired
    public ClientPage(final ClientRepository clientRepository) {
        getLogger().debug("Construindo tela do cliente...");
        
        final Client cliente = clientRepository.findById(1l);
        final Map<Tab, Component> tabsToPages = new HashMap<>();

        final Tab tabUser = new Tab("Diego Fincatto");
        //tabUser.setEnabled(false);
        final Div divUser = new Div();
        //divUser.setClassName("user_div");
        divUser.setText("Usuario");
        tabsToPages.put(tabUser, divUser);

        final Tab tabPedidos = new Tab("Pedidos");
        final Div divPedidos = new Div();
        divPedidos.setVisible(false);
        final VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(new Label("Teste 1"), new Label("Teste 2"));
        divPedidos.add(verticalLayout);
        tabsToPages.put(tabPedidos, divPedidos);

        final Tab tabNotas = new Tab("Notas");
        final Div divNotas = new Div();
        //divNotas.setSizeFull();
        
        final Grid<Invoice> invoiceGrid = new Grid<>(Invoice.class, false);
        invoiceGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COMPACT, GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        invoiceGrid.addColumn(Invoice::getNumero).setHeader("Numero");
        invoiceGrid.addColumn(Invoice::getQuantidade).setHeader("Qtd");
        invoiceGrid.addColumn(Invoice::getValorUnitario).setHeader("Valor");
        invoiceGrid.addColumn(Invoice::getValorTotal).setHeader("Total");
        //invoiceGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        invoiceGrid.setItems(cliente.getInvoices());
        invoiceGrid.setMultiSort(true);
        //invoiceGrid.setHeightFull();
        //invoiceGrid.setSizeFull();
        
        final Button adicionar = new Button("Adicionar");
        adicionar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        //adicionar.addThemeVariants(ButtonVariant.LUMO_SMALL);
        
        final Button cancelar = new Button("Cancelar");
        cancelar.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);
        //cancelar.addThemeVariants(ButtonVariant.LUMO_SMALL);
        
        final HorizontalLayout horizontalLayoutAcoes = new HorizontalLayout();
        horizontalLayoutAcoes.setAlignItems(FlexComponent.Alignment.START);
        horizontalLayoutAcoes.add(adicionar, cancelar);
        horizontalLayoutAcoes.setMargin(false);
        
        final VerticalLayout notasVerticalLayout = new VerticalLayout();
        //notasVerticalLayout.add(new H5("Notas"), invoiceGrid, horizontalLayoutAcoes);
        notasVerticalLayout.add(invoiceGrid, horizontalLayoutAcoes);
        //notasVerticalLayout.setSizeFull();
        
        divNotas.add(notasVerticalLayout);
        divNotas.setVisible(false);
        tabsToPages.put(tabNotas, divNotas);

        final Div pages = new Div(divUser, divPedidos, divNotas);
        //pages.setClassName("user_divs");

        final Set<Component> pagesShown = Stream.of(divUser).collect(Collectors.toSet());

        final Tabs tabs = new Tabs(tabUser, tabPedidos, tabNotas, new Tab("Cobranças"), new Tab("Históricos"), new Tab("Tab six"), new Tab("Tab seven"), new Tab("Tab eight"), new Tab("Tab nine"), new Tab("Tab ten"), new Tab("Tab eleven"), new Tab("Tab twelve"), new Tab("Tab thirteen"), new Tab("Tab fourteen"), new Tab("Tab fifteen"));
        tabs.addThemeVariants(TabsVariant.LUMO_SMALL);
        //tabs.setWidth("100%");
        //tabs.addClassName("user_tabs");

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
        //this.getContent().setSizeFull();
    }
}
