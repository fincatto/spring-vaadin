package com.fincatto.springvaadin.components;

import com.fincatto.springvaadin.Loggable;
import com.fincatto.springvaadin.classes.User;
import com.fincatto.springvaadin.repositories.UserRepository;
import com.fincatto.springvaadin.views.HomePage;
import com.fincatto.springvaadin.views.UsersPage;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinService;

public class TopoComponent extends HorizontalLayout implements Loggable {

    public TopoComponent(final UserRepository userRepository) {
        this.setPadding(true);
        this.setWidth("100%");
        this.setHeight("50px");
        this.addClassName("header");
        this.setDefaultVerticalComponentAlignment(Alignment.CENTER);

        final HorizontalLayout menuEsquerda = new HorizontalLayout();
        menuEsquerda.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        menuEsquerda.setSizeFull();

        //final Icon menuIcon = VaadinIcon.MENU.create();
        final RouterLink linkHome = new RouterLink("Admin", HomePage.class);
        linkHome.addClassName("link_home");

        final RouterLink linkUsers = new RouterLink("Users", UsersPage.class);

        final TextField tfPesquisa = new TextField();
        tfPesquisa.setPlaceholder("Pesquisa...");
        tfPesquisa.setPrefixComponent(VaadinIcon.SEARCH.create());
        tfPesquisa.addKeyPressListener(Key.ENTER, e -> getLogger().debug("Pesquisando por {}...", tfPesquisa.getValue()));
        //menuEsquerda.add(menuIcon, linkHome, linkUsers, tfPesquisa);
        menuEsquerda.add(linkHome, linkUsers, tfPesquisa);

        final HorizontalLayout menuDireita = new HorizontalLayout();
        menuDireita.setDefaultVerticalComponentAlignment(Alignment.CENTER);

        final ComboBox<User> comboBox = new ComboBox<>();
        comboBox.setPreventInvalidInput(true);
        comboBox.setItems(userRepository.findAll());
        //comboBox.setDataProvider(DataProvider.fromFilteringCallbacks(query -> userRepository.findByName(query.getFilter().toString()).stream(), query -> userRepository.findByName(query.getFilter().toString()).size()));
        comboBox.setItemLabelGenerator(User::getNome);
        comboBox.setRenderer(TemplateRenderer.<User>of("<div>[[item.nome]]<br><small>[[item.email]]</small></div>").withProperty("nome", User::getNome).withProperty("email", User::getEmail));
        comboBox.addValueChangeListener(event -> {
            if (event.getSource().isEmpty()) {
                VaadinService.getCurrentRequest().removeAttribute("selectedUser");
                getLogger().debug("Removendo usuario selecionado...");
            } else if (event.getOldValue() == null) {
                getLogger().debug("Setando novo usuario selecionado: {}", event.getValue().getNome());
                VaadinService.getCurrentRequest().setAttribute("selectedUser", event.getValue());
                getUI().ifPresent(ui -> ui.navigate(HomePage.class));
            } else {
                VaadinService.getCurrentRequest().setAttribute("selectedUser", event.getValue());
                getLogger().debug("Trocando usuario {} por: {}", event.getOldValue().getNome(), event.getValue().getNome());
                getUI().ifPresent(ui -> ui.navigate(HomePage.class));
            }
        });
        menuDireita.add(comboBox);

        this.add(menuEsquerda, menuDireita);
    }
}
