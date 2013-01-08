
package org.netbeans.spi.editor.completion.xhtml.api;

import java.net.URI;
import java.util.List;

/**
 * <p>Defines a completion item provider.</p>
 * 
 * <p>One must check to provider if its able to server completion items.</p>
 * 
 * @author oschmitt
 */
public interface CompletionItemProvider {
    
    /**
     * <p>Is this provider able to serve content with this scheme ?</p>
     * 
     * @param scheme The first part of an URI identifying a provider
     * @return <code>true</code> if this provider match the scheme
     */
    boolean accept(String scheme);
    
    /**
     * <p>Provide completion item values for completion list population.</p>
     * 
     * @param query Current completion query
     * @return A list of CompletionItemValue as a response to completion query
     */
    List<CompletionItemValue> getCompletionItemValues(String query);

    /**
     * <p>Configure current provider with URI.</p>
     * 
     * <p>The scheme URI must be accepted by the provider.</p>
     * 
     * @param uri URI of content
     * @throws org.netbeans.spi.editor.completion.xhtml.api.CompletionItemProvider.CompletionConfigurationException 
     */
    void configure(URI uri) throws CompletionConfigurationException;
    
    /**
     * <p>Defines a configuration exception.</p>
     */
    public static class CompletionConfigurationException extends Exception {

        public CompletionConfigurationException() {
            super("Can not configure provider");
        }

        public CompletionConfigurationException(Exception e) {
            super("Can not configure provider",e);
        }
    }
}
