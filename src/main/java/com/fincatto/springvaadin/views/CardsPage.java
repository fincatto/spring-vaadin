package com.fincatto.springvaadin.views;

import com.fincatto.springvaadin.layouts.TemplatePrincipalLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Cards")
@Route(value = "cards", layout = TemplatePrincipalLayout.class)
public class CardsPage extends Composite<VerticalLayout> {
    
    public CardsPage() {
        final HorizontalLayout header = new HorizontalLayout(new H4("Cards"));
        header.setWidthFull();
        header.setMargin(false);
        header.setSpacing(false);
        
        final FlexLayout cardsLayout = new FlexLayout();
        //cardsLayout.setWidthFull();
        cardsLayout.setWrapMode(FlexLayout.WrapMode.WRAP);
        cardsLayout.setAlignItems(FlexComponent.Alignment.START);
        //cardsLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.EVENLY);
        cardsLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        for (int i = 0; i < 100; i++) {
            Div div = new Div();
            div.add(new Image("https://capas-c.ewmix.com/15563.jpg", "Capa do produto"));
            
            final Div produtoTitulo = new Div(new Label("Produto Tal"));
            produtoTitulo.addClassName("bold");
            div.add(produtoTitulo);
            
            final Div produtoSubtitulo = new Div(new Label("Desrição detalhada do prod"));
            div.add(produtoSubtitulo);
            
            //div.add(new Hr());
            //final NumberField numberField = new NumberField();
            //            final Icon iconeRemover = VaadinIcon.MINUS.create();
            //            iconeRemover.addClickListener(cl -> {
            //                numberField.setValue(numberField.getValue() != null && numberField.getValue() > 0 ? numberField.getValue() - 1 : 0);
            //            });
            //            numberField.setPrefixComponent(iconeRemover);
            //
            //            final Icon iconeAdicionar = VaadinIcon.PLUS.create();
            //            iconeAdicionar.addClickListener(cl -> {
            //                numberField.setValue(numberField.getValue() != null ? numberField.getValue() + 1 : 1);
            //            });
            //            numberField.setSuffixComponent(iconeAdicionar);
            //            div.add(numberField);
            
            //            final Button botaoComprar = new Button("Comprar agora");
            //            botaoComprar.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SMALL);
            //            div.add(botaoComprar);
            //            final Button botaoCarrinho = new Button("Adicionar ao carrinho");
            //            botaoCarrinho.addThemeVariants(ButtonVariant.LUMO_SMALL);
            //            div.add(botaoCarrinho);
            cardsLayout.add(div);
        }
        
        this.getContent().setSizeFull();
        this.getContent().setMargin(false);
        //this.getContent().setSpacing(false);
        final Hr hr = new Hr();
        hr.setMinHeight("1px");
        this.getContent().add(header, hr, cardsLayout);
    }
}
