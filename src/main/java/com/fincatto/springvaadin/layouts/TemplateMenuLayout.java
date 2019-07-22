package com.fincatto.springvaadin.layouts;

import com.fincatto.springvaadin.components.TopoComponent;
import com.fincatto.springvaadin.repositories.UserRepository;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.RouterLayout;
import org.springframework.beans.factory.annotation.Autowired;

@HtmlImport("styles/shared-styles.html")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class TemplateMenuLayout extends VerticalLayout implements RouterLayout {

    @Autowired
    public TemplateMenuLayout(final UserRepository userRepository) {
//        this.setMargin(false);
//        this.setSpacing(false);
//        this.setPadding(false);
//        this.setSizeFull();
        this.add(new TopoComponent(userRepository));
    }
}
