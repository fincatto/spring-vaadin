package com.fincatto.springvaadin.views;

import com.fincatto.springvaadin.Loggable;
import com.fincatto.springvaadin.classes.Client;
import com.fincatto.springvaadin.classes.Invoice;
import com.fincatto.springvaadin.layouts.TemplatePrincipalLayout;
import com.fincatto.springvaadin.repositories.ClientRepository;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Grid")
@Route(value = "grid", layout = TemplatePrincipalLayout.class)
public class GridPage extends Composite<VerticalLayout> implements Loggable {
    
    @Autowired
    public GridPage(final ClientRepository clientRepository) {
        getLogger().debug("Construindo tela do cliente...");
        final Client cliente = clientRepository.findById(1l);
        
        final HorizontalLayout header = new HorizontalLayout(new H4("Notas"));
        header.setWidthFull();
        header.setMargin(false);
        header.setSpacing(false);
        
        final Grid<Invoice> grid = new Grid<>(Invoice.class, false);
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COMPACT, GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        grid.addColumn(Invoice::getNumero).setHeader("Numero");
        grid.addColumn(Invoice::getQuantidade).setHeader("Qtd");
        grid.addColumn(Invoice::getValorUnitario).setHeader("Valor");
        grid.addColumn(Invoice::getValorTotal).setHeader("Total");
        //invoiceGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.setItems(cliente.getInvoices());
        grid.setMultiSort(true);
        grid.setSizeFull();
        //invoiceGrid.setHeightFull();
        //invoiceGrid.setSizeFull();
        
        final Button adicionar = new Button("Adicionar");
        //adicionar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        //adicionar.addThemeVariants(ButtonVariant.LUMO_SMALL);
        
        final Button cancelar = new Button("Cancelar");
        cancelar.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);
        //cancelar.addThemeVariants(ButtonVariant.LUMO_SMALL);
        
        final HorizontalLayout horizontalLayoutAcoes = new HorizontalLayout();
        horizontalLayoutAcoes.setAlignItems(FlexComponent.Alignment.START);
        horizontalLayoutAcoes.add(cancelar, adicionar);
        horizontalLayoutAcoes.setMargin(false);
        
        this.getContent().setSizeFull();
        this.getContent().setMargin(false);
        this.getContent().setSpacing(false);
        this.getContent().add(header, new Hr(), grid, horizontalLayoutAcoes);
    }
}
