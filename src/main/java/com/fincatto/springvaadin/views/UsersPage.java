package com.fincatto.springvaadin.views;

import com.fincatto.springvaadin.Loggable;
import com.fincatto.springvaadin.classes.User;
import com.fincatto.springvaadin.components.WMXVerticalLayoutComposite;
import com.fincatto.springvaadin.layouts.TemplatePrincipalLayout;
import com.fincatto.springvaadin.repositories.UserRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Usuarios")
@Route(value = "users", layout = TemplatePrincipalLayout.class)
public class UsersPage extends WMXVerticalLayoutComposite implements Loggable {

    @Autowired
    public UsersPage(final UserRepository userRepository) {
        final Grid<User> grid = new Grid<>();
        grid.addColumn(User::getNome).setHeader("Nome").setWidth("100px").setFlexGrow(0);
        grid.addColumn(User::getEmail).setHeader("Email");
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.setDataProvider(DataProvider.fromCallbacks(q -> userRepository.findByOffset(q.getOffset(), q.getLimit()).stream(), q -> userRepository.count()));
        grid.setPageSize(30);

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
        this.getContent().add(new H3("Teste"), grid, botoes);
        this.getContent().setSizeFull();
    }
}
