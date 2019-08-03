package com.fincatto.springvaadin.layouts;

import com.fincatto.springvaadin.Loggable;
import com.fincatto.springvaadin.components.WMXLink;
import com.fincatto.springvaadin.repositories.UserRepository;
import com.fincatto.springvaadin.views.ClientPage;
import com.fincatto.springvaadin.views.FornecedorPage;
import com.fincatto.springvaadin.views.HomePage;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.VaadinService;
import org.springframework.beans.factory.annotation.Autowired;

@HtmlImport("styles/shared-styles.html")
@Viewport("width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes, viewport-fit=cover")
@PreserveOnRefresh
@PageTitle("Home")
@PWA(name = "Sistema Admin", shortName = "SysAdmin", enableInstallPrompt = false)
public class TemplateMenuNativoLayout extends AppLayout implements Loggable {
    
    @Autowired
    public TemplateMenuNativoLayout(final UserRepository userRepository) {
        getLogger().debug("Iniciando construcao do template...");
    
        super.addToNavbar(new DrawerToggle());
        super.addToNavbar(new H4("Wmix Admin"));
    
        //menu acordeon
        final Accordion accordion = new Accordion();
    
        final AccordionPanel accordionPanelFornecedor = accordion.add("Diego Fincatto", new RouterLink("Dashboard", ClientPage.class));
        accordionPanelFornecedor.addContent(new Div(new RouterLink("Pagamentos", ClientPage.class)));
        accordionPanelFornecedor.addContent(new Div(new RouterLink("Entradas", ClientPage.class)));
        accordionPanelFornecedor.addThemeVariants(DetailsVariant.REVERSE, DetailsVariant.FILLED, DetailsVariant.SMALL);
    
        final AccordionPanel accordionPanelBanco = accordion.add("Banco do Brasil", new RouterLink("Arquivos", ClientPage.class));
        accordionPanelBanco.addThemeVariants(DetailsVariant.REVERSE, DetailsVariant.FILLED, DetailsVariant.SMALL);
        accordionPanelBanco.setVisible(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("banco") != null);
        accordionPanelBanco.addContent(new Div(new RouterLink("Movimentações", ClientPage.class)));
        accordionPanelBanco.addContent(new Div(new RouterLink("Pagamentos", ClientPage.class)));
        accordionPanelBanco.addContent(new Div(new WMXLink("Sair", e -> {
            new Notification("Vai cavalo!!!!", 2000).open();
            VaadinService.getCurrentRequest().getWrappedSession().removeAttribute("banco");
            accordionPanelFornecedor.setOpened(true);
        })));
    
        // Textual link
        final AccordionPanel accordionPanelSemBanco = accordion.add("Contas bancárias", new RouterLink("Banco do Brasil", ClientPage.class));
        accordionPanelSemBanco.addContent(new Div(new WMXLink("Exibir menu dos bancos", l -> {
            new Notification("Bancos ativos!", 3000).open();
            VaadinService.getCurrentRequest().getWrappedSession().setAttribute("banco", "DIEGO");
            accordionPanelBanco.setOpened(true);
            accordionPanelBanco.setVisible(true);
        })));
        accordionPanelSemBanco.addContent(new Div(new RouterLink("Caixa Economica", ClientPage.class)));
        accordionPanelSemBanco.addContent(new Div(new RouterLink("CitiBank", ClientPage.class)));
        accordionPanelSemBanco.addThemeVariants(DetailsVariant.REVERSE, DetailsVariant.FILLED, DetailsVariant.SMALL);
        accordionPanelSemBanco.setVisible(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("banco") == null);
    
        final AccordionPanel accordionPanelCliente = accordion.add("Clientes", new RouterLink("Notas", ClientPage.class));
        accordionPanelCliente.addContent(new Div(new RouterLink("Pedidos", ClientPage.class)));
        accordionPanelCliente.addContent(new Div(new RouterLink("Trocas", ClientPage.class)));
        accordionPanelCliente.addThemeVariants(DetailsVariant.REVERSE, DetailsVariant.FILLED, DetailsVariant.SMALL);
    
        final AccordionPanel accordionPanelProdutos = accordion.add("Produtos", new RouterLink("Lancamentos", FornecedorPage.class));
        accordionPanelProdutos.addContent(new Div(new RouterLink("Catalogos", FornecedorPage.class)));
        accordionPanelProdutos.addThemeVariants(DetailsVariant.REVERSE, DetailsVariant.FILLED, DetailsVariant.SMALL);
        
        final AccordionPanel disabledPannel = accordion.add("Clientes", new RouterLink("Home", HomePage.class));
        disabledPannel.addThemeVariants(DetailsVariant.REVERSE, DetailsVariant.FILLED, DetailsVariant.SMALL);
        disabledPannel.setEnabled(false);
    
        final Div div = new Div(accordion);
        div.addClassName("menu");
        super.addToDrawer(div);
    
        //menu
        final MenuBar menuBar = new MenuBar();
    
        //busca
//        final TextField tfBusca = new TextField(null, "Pesquisa de dados...");
//        tfBusca.setWidth("100px");
//        tfBusca.addFocusListener(textFieldFocusEvent -> tfBusca.setWidth("300px"));
//        tfBusca.addBlurListener(textFieldFocusEvent -> tfBusca.setWidth("100px"));
//        menuBar.addItem(tfBusca);
        
        // Define menubar items
        final MenuItem menuItemFornecedor = menuBar.addItem("Diego Fincatto");
        //menuItemFornecedor.add(VaadinIcon.CARET_DOWN.create());
    
        //project.add(VaadinIcon.USER.create(), new Span("Diego Fincatto"), VaadinIcon.ARROW_DOWN.create());
        //final MenuItem account = menuBar.addItem("Banco do Brasil");
        //account.getSubMenu().addItem("Edit Profile");
        //account.getSubMenu().addItem("Privacy Settings");
    
        //final SubMenu projectSubMenu = menuItemFornecedor.getSubMenu();
        //final MenuItem users = projectSubMenu.addItem("Users");
        //final MenuItem billing = projectSubMenu.addItem("Billing");
        //
        //// Register actions to item selections
        //final SubMenu usersSubMenu = users.getSubMenu();
        //usersSubMenu.addItem("List", e -> this.getUI().ifPresent(ui -> ui.navigate(UsersPage.class)));
        //usersSubMenu.addItem("Add", e -> this.getUI().ifPresent(ui -> ui.navigate(ClientPage.class)));
        //
        //final SubMenu billingSubMenu = billing.getSubMenu();
        //billingSubMenu.addItem("Invoices");
        //billingSubMenu.addItem("Balance Events");
    
        //final HorizontalLayout horizontalLayout = new HorizontalLayout(menuBar);
        //horizontalLayout.setAlignItems(FlexComponent.Alignment.END);
        //horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        //horizontalLayout.setWidthFull();
        //super.addToNavbar(horizontalLayout);
    }
}

//@Route(value = "", layout = TemplateMenuNativoLayout.class)
//class HomeView extends Div {
//}

//@PageTitle("Sobre")
//@Route(value = "about", layout = TemplateMenuNativoLayout.class)
//class AboutView extends Div {
//    public AboutView() {
//        this.add(new Span("Sobre a empresa..."));
//    }
//}