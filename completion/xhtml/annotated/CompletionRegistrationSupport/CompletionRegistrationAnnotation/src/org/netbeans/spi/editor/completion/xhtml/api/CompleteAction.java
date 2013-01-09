
package org.netbeans.spi.editor.completion.xhtml.api;

import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 * <p>Defines a complete action.</p>
 * 
 * <p>The perform method is invoked when user choose a completion item.</p>
 * 
 * @author oschmitt
 */
public interface CompleteAction {
   
    /**
     * <p>Do the complete process : insert a value, nothing, ....</p>
     * 
     * @param completionItemData The item selected by the user
     * @param styledDocument The current document 
     * @param position The current position of attribute value to complete
     * @param length The length of user request (aka length of current attribute value)
     * @throws BadLocationException If document is updated with a bad position.
     */
    void perform(CompletionItemData completionItemData,StyledDocument styledDocument,
                    int position,int length) throws BadLocationException;
}