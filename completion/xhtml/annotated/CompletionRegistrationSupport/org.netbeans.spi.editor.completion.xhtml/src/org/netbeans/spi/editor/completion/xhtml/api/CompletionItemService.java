
package org.netbeans.spi.editor.completion.xhtml.api;

import java.net.URI;

/**
 * <p>Defines a completion item service.</p>
 * 
 * <p>A service should expose accept method and accept URI with a specific scheme.</p>
 *
 * <p>The configure method is called with complete URI.</p>
 * 
 * <p>Each service has its own way to be configured.</p>
 * 
 * @author oschmitt
 */
public interface CompletionItemService extends CompletionItemDataProvider {
    
    /**
     * <p>Is this provider able to serve content with this scheme ?</p>
     * 
     * @param scheme The first part of an URI identifying a provider
     * @return <code>true</code> if this provider match the scheme
     */
    boolean accept(String scheme);

    /**
     * <p>Configure current provider with URI.</p>
     * 
     * <p>The scheme URI must be accepted by the provider.</p>
     * 
     * @param uri URI of content
     * @throws org.netbeans.spi.editor.completion.xhtml.api.CompletionItemService.CompletionConfigurationException 
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
