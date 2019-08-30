package com.fincatto.springvaadin.components;

import com.fincatto.springvaadin.Loggable;
import com.vaadin.flow.component.Composite;

import java.util.ResourceBundle;

public class WMXVerticalLayoutComposite extends Composite<WMXVerticalLayout> implements Loggable {

    private ResourceBundle i18n;

    protected ResourceBundle getI18n() {
        if (this.i18n == null){
            this.i18n = ResourceBundle.getBundle(String.format("i18n/%s", getClass().getSimpleName()), getLocale());
        }
        return this.i18n;
    }
}
