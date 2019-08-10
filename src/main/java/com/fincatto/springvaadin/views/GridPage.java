package com.fincatto.springvaadin.views;

import com.fincatto.springvaadin.Loggable;
import com.fincatto.springvaadin.classes.Client;
import com.fincatto.springvaadin.classes.Invoice;
import com.fincatto.springvaadin.layouts.TemplatePrincipalLayout;
import com.fincatto.springvaadin.repositories.ClientRepository;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToBigDecimalConverter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.converter.StringToLongConverter;
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
        grid.setItems(cliente.getInvoices());
        grid.addColumn(i -> "Nota " + i.getNumero()).setHeader("Numero").setFlexGrow(0);
        grid.addColumn(Invoice::getQuantidade).setHeader("Qtd").setTextAlign(ColumnTextAlign.END).setFlexGrow(0);
        grid.addColumn(Invoice::getValorUnitario).setHeader("Valor").setTextAlign(ColumnTextAlign.END).setFlexGrow(0);
        grid.addColumn(Invoice::getValorTotal).setHeader("Total").setTextAlign(ColumnTextAlign.END).setFlexGrow(0);
        grid.addComponentColumn(i -> {
            final Button editar = new Button(VaadinIcon.EDIT.create(), cl -> editarNota(i, grid));
            //editar.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY_INLINE);
            editar.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
            editar.getElement().setProperty("title", "Editar nota fiscal");
            return editar;
        }).setHeader("Opções").setFlexGrow(1);
        //grid.getColumns().forEach(column -> column.setAutoWidth(true));
        //grid.addColumn(i -> "").setWidth("100%");
        grid.setSizeFull();
        //grid.setHeightFull();
        grid.setMultiSort(true);
        grid.setColumnReorderingAllowed(true);
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.getColumns().forEach(column -> column.setResizable(true));
        //grid.getHeaderRows().forEach(h-> h.getCells().forEach(c -> c.set));
        grid.addItemDoubleClickListener(l -> editarNota(l.getItem(), grid));
        
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
    
    private void editarNota(Invoice invoice, Grid<Invoice> grid) {
        final Binder<Invoice> binder = new BeanValidationBinder<>(Invoice.class);
        getLogger().debug("Editando invoice " + invoice.getNumero());
        
        final HorizontalLayout formHeader = new HorizontalLayout(new H4("Formulario"));
        formHeader.setWidthFull();
        formHeader.setMargin(false);
        formHeader.setSpacing(false);
        
        final TextField tfCodigo = new TextField();
        tfCodigo.setLabel("Codigo");
        tfCodigo.setEnabled(false);
        binder.forField(tfCodigo).withConverter(new StringToLongConverter("Codigo invalido")).bind(Invoice::getId, null);
        
        final TextField tfNumero = new TextField();
        tfNumero.setLabel("Numero");
        tfNumero.setAutofocus(true);
        binder.forField(tfNumero).withConverter(new StringToIntegerConverter("Numero invalido")).bind(Invoice::getNumero, Invoice::setNumero);
        
        final TextField tfQuantidade = new TextField();
        tfQuantidade.setLabel("Quantidade");
        binder.forField(tfQuantidade).withConverter(new StringToBigDecimalConverter("Quantidade invalida")).bind(Invoice::getQuantidade, Invoice::setQuantidade);
        
        final TextField tfValorUnitario = new TextField();
        tfValorUnitario.setLabel("Valor unitário");
        binder.forField(tfValorUnitario).withConverter(new StringToBigDecimalConverter("Valor unitário inválido")).bind(Invoice::getValorUnitario, Invoice::setValorUnitario);
        
        final FormLayout formLayout = new FormLayout();
        formLayout.add(tfCodigo, tfNumero, tfQuantidade, tfValorUnitario);
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1), new FormLayout.ResponsiveStep("600px", 2), new FormLayout.ResponsiveStep("1000px", 3), new FormLayout.ResponsiveStep("1400px", 4));
        binder.readBean(invoice);
        
        final Button botaoSalvar = new Button("Salvar", b -> {
            if (binder.writeBeanIfValid(invoice)) {
                grid.getDataProvider().refreshItem(invoice);
                grid.getDataProvider().refreshAll();
            } else {
                Notification.show("Deu ruim!");
            }
        });
        botaoSalvar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        final Button botaoCancelar = new Button("Cancelar", b -> {
            binder.readBean(invoice);
        });
        botaoCancelar.addThemeVariants(ButtonVariant.LUMO_ERROR);
        
        final HorizontalLayout horizontalLayout = new HorizontalLayout(botaoSalvar, botaoCancelar);
        
        final VerticalLayout vlForm = new VerticalLayout(formHeader, new Hr(), formLayout, horizontalLayout);
        vlForm.setMaxWidth("800px");
        
        final Dialog dialog = new Dialog(vlForm);
        botaoSalvar.addClickListener(cl -> dialog.close());
        botaoCancelar.addClickListener(cl -> dialog.close());
        dialog.open();
    }
}
