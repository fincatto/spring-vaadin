package com.fincatto.springvaadin.ui;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route(value = "pagina", layout = TemplateColunasLayout.class)
public class OtherPage extends Composite<Div> {

    public OtherPage() {
        final Dialog dialog = new Dialog();
        dialog.add(new PersonForm(dialog));
        this.getContent().add(new Button("Cadastro de pessoa", e -> dialog.open()));
    }
}
