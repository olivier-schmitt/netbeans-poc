/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jee.architect.cookbook.netbeans.iso6391;

import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import org.netbeans.spi.editor.completion.xhtml.api.CompleteAction;
import org.netbeans.spi.editor.completion.xhtml.api.CompletionItemData;

/**
 *
 * @author oschmitt
 */
public class ISO6391CompleteAction implements CompleteAction {

    @Override
    public void perform(CompletionItemData completionItemData, StyledDocument styledDocument, 
            int position, int length) throws BadLocationException {
        styledDocument.remove(position, length);
        styledDocument.insertString(position, completionItemData.getValue(), null);
    }
}
