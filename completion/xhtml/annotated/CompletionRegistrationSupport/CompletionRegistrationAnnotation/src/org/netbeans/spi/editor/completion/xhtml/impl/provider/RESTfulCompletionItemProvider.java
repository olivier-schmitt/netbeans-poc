/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.spi.editor.completion.xhtml.impl.provider;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.xhtml.api.CompletionItemProvider;
import org.netbeans.spi.editor.completion.xhtml.api.AttributeInCompletion;

/**
 *
 * @author oschmitt
 */
public class RESTfulCompletionItemProvider implements CompletionItemProvider {

    @Override
    public List<CompletionItem> getCompletionItem(AttributeInCompletion attribute, Map annotationConfMap) {
        return Collections.EMPTY_LIST;
    }
    
    @Override
    public boolean accept(String scheme) {
        return "rest".equals(scheme);
    }
    
    @Override
    public void configure(URI uri) throws CompletionConfigurationException {
        
        
    }
}
