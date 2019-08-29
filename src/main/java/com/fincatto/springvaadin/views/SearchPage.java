package com.fincatto.springvaadin.views;

import com.fincatto.springvaadin.Loggable;
import com.fincatto.springvaadin.components.WMXVerticalLayoutComposite;
import com.fincatto.springvaadin.layouts.TemplatePrincipalLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.apache.commons.lang3.StringUtils;

@PageTitle("Search")
@Route(value = "search", layout = TemplatePrincipalLayout.class)
public class SearchPage extends WMXVerticalLayoutComposite implements HasUrlParameter<String>, Loggable {

    public SearchPage() {
        getLogger().debug("Renderizando a pagina...");
        //this.getContent().setSizeFull();
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String busca) {
        this.getLogger().debug("Setando os parametros...");
        this.getContent().removeAll();
        if (StringUtils.isNotBlank(busca)) {
            final H2 header = new H2("Buscou por: " + busca);
            header.addClassName("sem_margem");

            final VerticalLayout verticalLayout = new VerticalLayout();
            verticalLayout.setMargin(false);
            verticalLayout.add(header);

            this.getContent().add(verticalLayout);
        }
    }
}
