package com.fincatto.springvaadin.layouts;

import com.fincatto.springvaadin.Loggable;
import com.fincatto.springvaadin.repositories.UserRepository;
import com.fincatto.springvaadin.views.ClientPage;
import com.fincatto.springvaadin.views.HomePage;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.BodySize;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;

//@HtmlImport("styles/shared-styles.html")
//@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
@Viewport("width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes, viewport-fit=cover")
@BodySize
//@Theme(Lumo.class)
public class TemplateMenuNativoLayout extends AppLayout implements Loggable {
    
    @Autowired
    public TemplateMenuNativoLayout(final UserRepository userRepository) {
        getLogger().debug("Iniciando...");
        final DrawerToggle drawerToggle = new DrawerToggle();
        addToNavbar(drawerToggle);
        addToNavbar(new Label("Wmix"));
    
    
        final Tabs tabs = new Tabs(new Tab("Home"), new Tab("About"));
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        addToDrawer(tabs);
    
        final Label secao1 = new Label("Secao 1");
        final RouterLink home = new RouterLink("Home", HomePage.class);
        final RouterLink about = new RouterLink("About Company", AboutView.class);
        final RouterLink cliente = new RouterLink("Cliente", ClientPage.class);
    
        final VerticalLayout layout = new VerticalLayout(secao1, home, about, cliente);
        layout.setSpacing(false);
        addToDrawer(layout);
    }
}


//@Route(value = "", layout = TemplateMenuNativoLayout.class)
//class HomeView extends Div {
//}

@Route(value = "about", layout = TemplateMenuNativoLayout.class)
class AboutView extends Div {
}