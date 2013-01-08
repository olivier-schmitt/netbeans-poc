/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.spi.editor.completion.xhtml.impl.provider;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import org.netbeans.spi.editor.completion.xhtml.api.CompletionItemProvider;
import org.netbeans.spi.editor.completion.xhtml.api.CompletionItemValue;

/**
 *
 * @author oschmitt
 */
public class RESTfulCompletionItemProvider implements CompletionItemProvider {

    
    @Override
    public boolean accept(String scheme) {
        return "rest".equals(scheme);
    }
    
    @Override
    public void configure(URI uri) throws CompletionConfigurationException {
        
        
    }

    @Override
    public List<CompletionItemValue> getCompletionItemValues(String query) {
        return Collections.EMPTY_LIST;
    }
}
