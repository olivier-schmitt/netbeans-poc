/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.spi.editor.completion.xhtml.api;

import org.netbeans.spi.editor.completion.xhtml.api.AttributeInCompletion;
import java.net.URI;
import java.util.List;
import java.util.Map;
import org.netbeans.spi.editor.completion.CompletionItem;

/**
 *
 * @author oschmitt
 */
public interface CompletionItemProvider {
    
    boolean accept(String scheme);
    
    List<CompletionItem> getCompletionItem(AttributeInCompletion attribute, Map annotationConfMap);

    void configure(URI uri) throws CompletionConfigurationException;
    
    public static class CompletionConfigurationException extends Exception {

        public CompletionConfigurationException() {
            super("Can not configure provider");
        }

        public CompletionConfigurationException(Exception e) {
            super("Can not configure provider",e);
        }
    }
}
