package com.fincatto.springvaadin.views;

import com.fincatto.springvaadin.Loggable;
import com.fincatto.springvaadin.classes.User;
import com.fincatto.springvaadin.repositories.UserRepository;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "pagina", layout = TemplateColunasLayout.class)
public class OtherPage extends Composite<VerticalLayout> implements Loggable  {

    @Autowired
    public OtherPage(UserRepository userRepository) {
        final Grid<User> grid = new Grid<>();
        //grid.setItems(userRepository.findAll());
        grid.addColumn(User::getId).setHeader("ID");
        grid.addColumn(User::getNome).setHeader("Nome");
        grid.addColumn(User::getEmail).setHeader("Email");
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.setMultiSort(true);
        grid.setPageSize(30);
        grid.setDataProvider(DataProvider.fromCallbacks(q -> userRepository.findByOffset(q.getOffset(), q.getLimit()).stream(), q -> userRepository.count()));

        final Dialog dialog = new Dialog();
        dialog.add(new PersonForm(dialog));
        final Button botaoNovo = new Button("Novo usuário", e -> dialog.open());

        final Button botaoExcluir = new Button("Excluir usuário", e -> grid.getSelectedItems().forEach(user -> getLogger().debug("Excluindo {}...", user.getId())));
        botaoExcluir.addClassName("danger");
        botaoExcluir.setVisible(false);

        final HorizontalLayout botoes = new HorizontalLayout(botaoNovo, botaoExcluir);
        this.getContent().add(grid, botoes);

        grid.addSelectionListener(sl -> botaoExcluir.setVisible(!grid.getSelectedItems().isEmpty()));
    }
}
