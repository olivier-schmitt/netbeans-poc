
package org.netbeans.spi.editor.completion.xhtml.impl;

import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import org.netbeans.spi.editor.completion.xhtml.api.CompleteAction;
import org.netbeans.spi.editor.completion.xhtml.api.CompletionItemData;

/**
 *
 * @author oschmitt
 */
public class DefaultCompleteAction implements CompleteAction {

    @Override
    public void perform(CompletionItemData completionItemData, StyledDocument styledDocument, 
            int position, int length) throws BadLocationException {
        
        styledDocument.remove(position, length);
        styledDocument.insertString(position, completionItemData.getValue(), null);
    }
    
}
