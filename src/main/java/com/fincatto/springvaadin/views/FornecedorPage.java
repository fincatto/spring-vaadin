package com.fincatto.springvaadin.views;

import com.fincatto.springvaadin.Loggable;
import com.fincatto.springvaadin.classes.User;
import com.fincatto.springvaadin.layouts.TemplateMenuLayout;
import com.fincatto.springvaadin.repositories.UserRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@PageTitle("Fornecedor")
@Route(value = "fornecedor", layout = TemplateMenuLayout.class)
public class FornecedorPage extends Composite<Div> implements Loggable {

    @Autowired
    public FornecedorPage(final UserRepository userRepository) {
        final Map<Tab, Component> tabsToPages = new HashMap<>();

        final Tab tabFornecedor = new Tab("Empresa da Silva");
        final Div divFornecedor = new Div();
        divFornecedor.setClassName("user_div");

        tabsToPages.put(tabFornecedor, divFornecedor);

        final Tab tabPagamentos = new Tab("Pagamentos");
        final Div divPagamentos = new Div();
        divPagamentos.setClassName("user_div");
        divPagamentos.setText("Pagamentos");
        divPagamentos.setVisible(false);
        tabsToPages.put(tabPagamentos, divPagamentos);

        final Tab tabNotas = new Tab("Notas");
        //final Div divNotas = new Div();
        //divNotas.setClassName("user_div");
        //divNotas.setVisible(false);

        final Grid<User> gridPagamentos = new Grid<>();
        gridPagamentos.addColumn(User::getNome).setHeader("Nome").setWidth("100px").setFlexGrow(0);
        gridPagamentos.addColumn(User::getEmail).setHeader("Email");
        gridPagamentos.setSelectionMode(Grid.SelectionMode.MULTI);
        gridPagamentos.setItems(userRepository.findAll());
        //gridPagamentos.setHeightByRows(true);
        //gridPagamentos.setDataProvider(DataProvider.fromCallbacks(q -> userRepository.findByOffset(q.getOffset(), q.getLimit()).stream(), q -> userRepository.count()));
        //gridPagamentos.setPageSize(30);
        //gridPagamentos.setMultiSort(true);
        gridPagamentos.setVisible(false);
        //gridPagamentos.setDetailsVisibleOnClick(true);

        tabsToPages.put(tabNotas, gridPagamentos);

        final Div pages = new Div(divFornecedor, divPagamentos, gridPagamentos);
        pages.setClassName("user_divs");
        //pages.setSizeFull();

        final Set<Component> pagesShown = Stream.of(divFornecedor).collect(Collectors.toSet());

        final Tabs tabs = new Tabs(tabFornecedor, tabPagamentos, tabNotas);
        tabs.setWidth("100%");
        tabs.addClassName("user_tabs");
        //tabs.setSizeFull();
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
        //gridPagamentos.setSizeFull();
    }
}
