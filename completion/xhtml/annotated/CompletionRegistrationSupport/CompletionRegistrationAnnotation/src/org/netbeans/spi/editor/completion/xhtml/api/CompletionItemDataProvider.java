
package org.netbeans.spi.editor.completion.xhtml.api;

import java.util.List;

/**
 * <p>Defines a provider of CompletionItemData.</p>
 * 
 * <p>Bean should implement this interface in combination with bean CompletionItemService.</p>
 * 
 * <p>When registering an attribute completion use content URI : "bean:monpkg.MyBeanCompletionItemService".</p>
 * 
 * @author oschmitt
 */
public interface CompletionItemDataProvider {
    
    /**
     * <p>Provides completion item datas for completion list population.</p>
     * 
     * @param query Current completion query
     * @return A list of CompletionItemData as a response to completion query
     */
    List<CompletionItemData> getDatas(String query);
}
