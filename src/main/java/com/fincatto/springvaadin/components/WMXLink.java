package com.fincatto.springvaadin.components;

import com.vaadin.flow.component.*;

@Tag("a")
public class WMXLink extends Component implements HasText, HasComponents, HasStyle, ClickNotifier<WMXLink> {
    
    private WMXLink() {
        getElement().getStyle().set("cursor", "pointer");
    }
    
    public WMXLink(String text) {
        this();
        this.setText(text);
    }
    
    public WMXLink(String text, ComponentEventListener<ClickEvent<WMXLink>> listener) {
        this(text);
        this.addClickListener(listener);
    }
}
