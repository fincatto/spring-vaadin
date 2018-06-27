package com.fincatto.springvaadin.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;

@Route("")
//@Theme(Lumo.class)
//@HtmlImport("frontend://styles/shared-styles.html")
//@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class UIMain extends VerticalLayout implements RouterLayout, PageConfigurator {
    //public class UIMain extends VerticalLayout {

    private TextField textFieldPesquisa = new TextField();

    @Override
    public void configurePage(InitialPageSettings initialPageSettings) {
    }

    public UIMain() {
        final Button button = new Button("Vai malandra!");
        button.addClickListener(e -> e.getSource().setText("Foi!"));

        final Button clearFilterTextBtn = new Button(new Icon(VaadinIcon.CLOSE_CIRCLE));
        clearFilterTextBtn.addClickListener(e -> textFieldPesquisa.clear());

        textFieldPesquisa.setValue("Aqui jas");
        textFieldPesquisa.setLabel("Text:");
        textFieldPesquisa.setPlaceholder("Filter by name...");
        textFieldPesquisa.setValueChangeMode(ValueChangeMode.EAGER);
        textFieldPesquisa.addValueChangeListener(e -> clearFilterTextBtn.setText(textFieldPesquisa.getValue()));

        FormLayout nameLayout = new FormLayout();
        TextField titleField = new TextField();
        titleField.setLabel("Title");
        titleField.setPlaceholder("Sir");
        TextField firstNameField = new TextField();
        firstNameField.setLabel("First name");
        firstNameField.setPlaceholder("John");
        TextField lastNameField = new TextField();
        lastNameField.setLabel("Last name");
        lastNameField.setPlaceholder("Doe");

        nameLayout.add(titleField, firstNameField, lastNameField);
        nameLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1), new FormLayout.ResponsiveStep("21em", 2), new FormLayout.ResponsiveStep("22em", 3));

        //adiciona os componentes na pagina
        final VerticalLayout verticalLayout = new VerticalLayout(button, clearFilterTextBtn, textFieldPesquisa, nameLayout);
        verticalLayout.setSizeFull();
        this.add(verticalLayout);
    }
}
