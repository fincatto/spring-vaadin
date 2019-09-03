package com.fincatto.springvaadin.views;

import com.fincatto.springvaadin.components.WMXHeader;
import com.fincatto.springvaadin.components.WMXVerticalLayoutComposite;
import com.fincatto.springvaadin.layouts.TemplatePrincipalLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Locale;

@PageTitle("I18n")
@Route(value = "i18n", layout = TemplatePrincipalLayout.class)
public class InternationalizationPage extends WMXVerticalLayoutComposite {
    
    public InternationalizationPage() {
        final WMXHeader header = new WMXHeader("I18n");
        
        //modelo de internacionalizacao por pagina
        final Label label1 = new Label(super.getI18n().getString("InternationalizationPage.texto"));
        
        //modelo global de intenacionalizacao
        final Label label2 = new Label(super.getTranslation("InternationalizationPage.texto"));
        final Label label3 = new Label(super.getTranslation("InternationalizationPage.textoInexistente"));
        final Label label4 = new Label(super.getTranslation("InternationalizationPage.texto", Locale.CHINA));
        
        //adiciona os componentes
        this.getContent().add(header, label1, label2, label3, label4);
    }
}