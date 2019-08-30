package com.fincatto.springvaadin.components;

import com.fincatto.springvaadin.Loggable;
import com.vaadin.flow.component.Composite;

import java.util.ResourceBundle;

public class WMXVerticalLayoutComposite extends Composite<WMXVerticalLayout> implements Loggable {

    private ResourceBundle bundle;

    protected ResourceBundle getBundle() {
        if (this.bundle == null){
            this.bundle = ResourceBundle.getBundle(String.format("i18n/%s", getClass().getSimpleName()), getLocale());
        }
        return this.bundle;
    }
}
