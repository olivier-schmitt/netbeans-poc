/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.spi.editor.completion.xhtml.impl.service;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import org.netbeans.spi.editor.completion.xhtml.api.CompletionItemData;
import org.netbeans.spi.editor.completion.xhtml.api.CompletionItemService;

/**
 *
 * @author oschmitt
 */
public class RESTfulCompletionItemService implements CompletionItemService {

    
    @Override
    public boolean accept(String scheme) {
        return "rest".equals(scheme);
    }
    
    @Override
    public void configure(URI uri) throws CompletionConfigurationException {
        
        
    }

    @Override
    public List<CompletionItemData> getDatas(String query) {
        return Collections.EMPTY_LIST;
    }
}
