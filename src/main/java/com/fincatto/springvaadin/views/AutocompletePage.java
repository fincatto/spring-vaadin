package com.fincatto.springvaadin.views;

import com.fincatto.springvaadin.Loggable;
import com.fincatto.springvaadin.layouts.TemplatePrincipalLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.stream.IntStream;

@PageTitle("Autocomplete")
@Route(value = "autocomplete", layout = TemplatePrincipalLayout.class)
public class AutocompletePage extends Composite<Div> implements Loggable {

    public AutocompletePage() {
        final Label valor = new Label("Nenhum valor selecionado");
        final ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setDataProvider((filter, offset, limit) -> {
            getLogger().debug("Buscando por '{}'...", filter);
            return IntStream.range(offset, offset + limit).mapToObj(i -> filter + " " + i);
        }, filter -> 100);
        comboBox.addValueChangeListener(l -> {
            valor.setText(l.getValue() != null ? l.getValue() : "nenhum selecionado");
        });
        comboBox.focus();
        this.getContent().add(comboBox, valor);
    }

}
