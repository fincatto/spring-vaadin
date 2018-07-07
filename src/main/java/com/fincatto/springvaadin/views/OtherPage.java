package com.fincatto.springvaadin.views;

import com.fincatto.springvaadin.classes.User;
import com.fincatto.springvaadin.repositories.UserRepository;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "pagina", layout = TemplateColunasLayout.class)
public class OtherPage extends Composite<VerticalLayout> {

    @Autowired
    public OtherPage(UserRepository userRepository) {
        final Grid<User> grid = new Grid<>();
        //grid.setItems(userRepository.findAll());
        grid.addColumn(User::getId).setHeader("ID");
        grid.addColumn(User::getNome).setHeader("Nome");
        grid.addColumn(User::getEmail).setHeader("Email");
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.setPageSize(30);
        //grid.setMultiSort(true);
        //grid.setDataProvider(DataProvider.fromCallbacks(query -> IntStream.range(query.getOffset(),query.getOffset() + query.getLimit()).mapToObj(index -> createPerson(index + 1, random)),query -> 100 * 1000 * 1000));
        grid.setDataProvider(DataProvider.fromCallbacks(q -> userRepository.findByOffset(q.getOffset(), q.getLimit()).stream(), q -> userRepository.count()));
        this.getContent().add(grid);

        final Dialog dialog = new Dialog();
        dialog.add(new PersonForm(dialog));
        this.getContent().add(new Button("Cadastro de pessoa", e -> dialog.open()));
    }
}
