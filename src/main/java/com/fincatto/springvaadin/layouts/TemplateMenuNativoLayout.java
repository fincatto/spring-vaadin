package com.fincatto.springvaadin.layouts;

import com.fincatto.springvaadin.Loggable;
import com.fincatto.springvaadin.repositories.UserRepository;
import com.fincatto.springvaadin.views.ClientPage;
import com.fincatto.springvaadin.views.HomePage;
import com.fincatto.springvaadin.views.UsersPage;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;

//@HtmlImport("styles/shared-styles.html")
//@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
@Viewport("width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes, viewport-fit=cover")
//@BodySize
@PreserveOnRefresh
//@Theme(Lumo.class)
//@Theme(Material.class)
@PageTitle("Home")
public class TemplateMenuNativoLayout extends AppLayout implements Loggable {
    
    @Autowired
    public TemplateMenuNativoLayout(final UserRepository userRepository) {
        getLogger().debug("Iniciando...");
    
        super.addToNavbar(new DrawerToggle());
        super.addToNavbar(new H3("Wmix"));
    
        final HorizontalLayout horizontalLayout1 = new HorizontalLayout();
        horizontalLayout1.setWidthFull();
        super.addToNavbar(horizontalLayout1);
        
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        for (int i = 1; i < 5; i++) {
            final Tab titulo = new Tab(String.format("Secao %s", i));
            titulo.setEnabled(false);
            tabs.add(titulo, new Tab("Home"), new Tab("About"));
        }
        super.addToDrawer(tabs);
        
        final MenuBar menuBar = new MenuBar();
        
        // Define menubar items
        final MenuItem menuItemFornecedor = menuBar.addItem("Diego Fincatto");
        //menuItemFornecedor.add(VaadinIcon.CARET_DOWN.create());
    
        //project.add(VaadinIcon.USER.create(), new Span("Diego Fincatto"), VaadinIcon.ARROW_DOWN.create());
        final MenuItem account = menuBar.addItem("Banco do Brasil");
    
        final SubMenu projectSubMenu = menuItemFornecedor.getSubMenu();
        final MenuItem users = projectSubMenu.addItem("Users");
        final MenuItem billing = projectSubMenu.addItem("Billing");
        
        // Register actions to item selections
        final SubMenu usersSubMenu = users.getSubMenu();
        usersSubMenu.addItem("List", e -> this.getUI().ifPresent(ui -> ui.navigate(UsersPage.class)));
        usersSubMenu.addItem("Add", e -> this.getUI().ifPresent(ui -> ui.navigate(ClientPage.class)));
    
        final SubMenu billingSubMenu = billing.getSubMenu();
        billingSubMenu.addItem("Invoices");
        billingSubMenu.addItem("Balance Events");
        
        account.getSubMenu().addItem("Edit Profile");
        account.getSubMenu().addItem("Privacy Settings");
    
        final HorizontalLayout horizontalLayout = new HorizontalLayout(menuBar);
        //horizontalLayout.setAlignItems(FlexComponent.Alignment.END);
        super.addToNavbar(horizontalLayout);
        
        for (int i = 1; i < 5; i++) {
            final H6 secao1 = new H6(String.format("Secao %s", i));
            final RouterLink home = new RouterLink("Home", HomePage.class);
            final RouterLink about = new RouterLink("About Company", AboutView.class);
            final RouterLink cliente = new RouterLink("Cliente", ClientPage.class);
            
            final VerticalLayout layout = new VerticalLayout(secao1, home, about, cliente);
            layout.setSpacing(false);
            layout.setMargin(false);
            super.addToDrawer(layout);
        }
    }
}

//@Route(value = "", layout = TemplateMenuNativoLayout.class)
//class HomeView extends Div {
//}

@PageTitle("Sobre")
@Route(value = "about", layout = TemplateMenuNativoLayout.class)
class AboutView extends Div {
    public AboutView() {
        this.add(new Span("Sobre a empresa..."));
    }
}