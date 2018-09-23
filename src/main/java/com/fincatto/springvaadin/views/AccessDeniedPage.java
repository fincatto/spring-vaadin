package com.fincatto.springvaadin.views;

import com.fincatto.springvaadin.layouts.TemplateSimplesLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "accessDenied", layout = TemplateSimplesLayout.class)
public class AccessDeniedPage extends Div {

    public AccessDeniedPage() {

        //final Div conteudo = new Div();
        this.addClassName("center");

        //this.getContent().addClassName("jumbotron");
        final H2 titulo = new H2("Acesso negado");
        titulo.addClassName("center");
        //conteudo.add(titulo);

        final Div detalhe = new Div(new Label("Chola mais, pq ta pouco"));
        detalhe.addClassName("center");
        //conteudo.add(detalhe);

        //this.getContent().add(conteudo);
        //this.getContent().add(titulo, detalhe);
        this.add(titulo, detalhe);
    }
}

