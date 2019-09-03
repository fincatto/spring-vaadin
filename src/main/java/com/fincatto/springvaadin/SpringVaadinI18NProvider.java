package com.fincatto.springvaadin;

import com.vaadin.flow.i18n.I18NProvider;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SpringVaadinI18NProvider implements I18NProvider, Loggable {
    
    private Map<Locale, ResourceBundle> i18n;
    
    @Override
    public List<Locale> getProvidedLocales() {
        return Arrays.asList(new Locale("pt", "BR"), new Locale("us", "EN"));
    }
    
    @Override
    public String getTranslation(String string, Locale locale, Object... objects) {
        //getLogger().debug("Traduzindo: '{}' para '{}' com objetos '{}'", string, locale.toLanguageTag(), objects);
        return this.getI18n(locale).containsKey(string) ? this.getI18n(locale).getString(string) : string;
    }
    
    /**
     * Faz um cache de resources, inicializando somente o que precisa, quando precisa.
     *
     * @param locale Locale do resource a ser utilizado.
     * @return ResourceBunde encontrado.
     */
    private ResourceBundle getI18n(final Locale locale) {
        //se nao tiver nenhum criado, crio o cache
        if (this.i18n == null) {
            this.i18n = new HashMap<>();
        }
        
        //se o locale nao existir, crio e adiciono no cache
        if (!this.i18n.containsKey(locale)) {
            this.i18n.put(locale, ResourceBundle.getBundle("i18n/SpringVaadin", locale));
        }
        
        //retorna o objeto ja cacheado
        return this.i18n.get(locale);
    }
}
