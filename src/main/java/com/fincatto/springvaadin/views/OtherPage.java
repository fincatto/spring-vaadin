package com.fincatto.springvaadin.views;

import com.fincatto.springvaadin.Loggable;
import com.fincatto.springvaadin.classes.User;
import com.fincatto.springvaadin.repositories.UserRepository;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "pagina", layout = TemplateColunasLayout.class)
public class OtherPage extends Composite<VerticalLayout> implements Loggable {

    @Autowired
    public OtherPage(UserRepository userRepository) {
        final Grid<User> grid = new Grid<>();
        //grid.addColumn(User::getId).setHeader("ID").setWidth("40px").setFlexGrow(0);
        grid.addColumn(User::getNome).setHeader("Nome").setWidth("100px").setFlexGrow(0);
        grid.addColumn(User::getEmail).setHeader("Email");
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.setColumnReorderingAllowed(true);
        grid.setMultiSort(true);
        grid.setPageSize(30);
        //grid.setItems(userRepository.findAll());
        grid.setDataProvider(DataProvider.fromCallbacks(q -> userRepository.findByOffset(q.getOffset(), q.getLimit()).stream(), q -> userRepository.count()));

        final Dialog dialog = new Dialog();
        dialog.add(new PersonForm(dialog));
        final Button botaoNovo = new Button("Novo usuário", e -> dialog.open());
        botaoNovo.addClassName("botao_primario");

        final Button botaoExcluir = new Button("Excluir", e -> grid.getSelectedItems().forEach(user -> getLogger().debug("Excluindo {}...", user.getId())));
        botaoExcluir.addClassName("botao_vermelho");
        botaoExcluir.setVisible(false);

        final Button botaoInativar = new Button("Inativar", e -> grid.getSelectedItems().forEach(user -> getLogger().debug("Inativando {}...", user.getId())));
        botaoInativar.addClassName("botao_amarelo");
        botaoInativar.setVisible(false);

        grid.addSelectionListener(sl -> botaoExcluir.setVisible(!grid.getSelectedItems().isEmpty()));
        grid.addSelectionListener(sl -> botaoInativar.setVisible(!grid.getSelectedItems().isEmpty()));

        final HorizontalLayout botoes = new HorizontalLayout(botaoNovo, botaoExcluir, botaoInativar);
        this.getContent().add(grid, botoes);
        this.getContent().setSizeFull();
    }
}
