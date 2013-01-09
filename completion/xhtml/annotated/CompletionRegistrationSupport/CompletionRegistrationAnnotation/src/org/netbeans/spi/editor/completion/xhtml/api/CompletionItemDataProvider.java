/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.spi.editor.completion.xhtml.api;

import java.util.List;

/**
 *
 * @author oschmitt
 */
public interface CompletionItemDataProvider {
    /**
     * <p>Provide completion item datas for completion list population.</p>
     * 
     * @param query Current completion query
     * @return A list of CompletionItemData as a response to completion query
     */
    List<CompletionItemData> getDatas(String query);
}
