package com.fincatto.springvaadin.components;

import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class WMXHeader extends VerticalLayout {

    public WMXHeader(String text) {
        final HorizontalLayout header = new HorizontalLayout(new H4(text));
        header.setWidthFull();
        header.setMargin(false);
        header.setSpacing(false);

        final Hr hr = new Hr();
        hr.setMinHeight("1px");

        this.add(header, hr);
        this.setMargin(false);
        this.setSpacing(false);
        this.setPadding(false);
    }
}
