package com.fincatto.springvaadin.views;

import com.fincatto.springvaadin.classes.Produto;
import com.fincatto.springvaadin.components.WMXHeader;
import com.fincatto.springvaadin.components.WMXProdutoComponent;
import com.fincatto.springvaadin.components.WMXVerticalLayoutComposite;
import com.fincatto.springvaadin.layouts.TemplatePrincipalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.security.access.annotation.Secured;

@PageTitle("Cards")
@Secured({"INEXISTENTE", "ADMIN"})
@Route(value = "cards", layout = TemplatePrincipalLayout.class)
public class CardsPage extends WMXVerticalLayoutComposite {
    
    public CardsPage() {
        final WMXHeader header = new WMXHeader("Cards");
        
        final FlexLayout cardsLayout = new FlexLayout();
        cardsLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        cardsLayout.setAlignItems(FlexComponent.Alignment.START);
        cardsLayout.setWrapMode(FlexLayout.WrapMode.WRAP);
        cardsLayout.setWidthFull();
        
        for (int i = 1; i < 100; i++) {
            final Produto produto = new Produto().setId((long) i).setTitulo(String.format("Produto %s", i)).setDescricao(String.format("Descrição detalhada %s", i)).setUrlCapa("https://capas-c.ewmix.com/15563.jpg");
            cardsLayout.add(new WMXProdutoComponent(produto));
        }
        
        this.getContent().add(header, cardsLayout);
        this.getContent().setMargin(false);
        this.getContent().setSizeFull();
    }
}
