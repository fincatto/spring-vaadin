package com.fincatto.springvaadin.components;

import com.fincatto.springvaadin.classes.Produto;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;

public class WMXProdutoComponent extends Div {
    
    public WMXProdutoComponent(Produto produto) {
        this.setVisible(produto != null);
        if (this.isVisible()) {
            final Image imagemCapa = new Image(produto.getUrlCapa(), "Capa do produto");
            imagemCapa.setWidthFull();
            this.add(imagemCapa);
            
            final Div divProdutoTitulo = new Div(new Label(produto.getTitulo()));
            divProdutoTitulo.addClassName("bold");
            this.add(divProdutoTitulo);
            
            final Div divProdutoSubtitulo = new Div(new Label(produto.getDescricao()));
            this.add(divProdutoSubtitulo);
            
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
        }
    }
}
