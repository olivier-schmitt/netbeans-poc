package org.netbeans.spi.editor.completion.xhtml.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.netbeans.api.editor.completion.Completion;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionResultSet;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.netbeans.spi.editor.completion.support.AsyncCompletionQuery;
import org.netbeans.spi.editor.completion.support.AsyncCompletionTask;
import org.netbeans.spi.editor.completion.support.CompletionUtilities;
import org.netbeans.spi.editor.completion.xhtml.api.CompleteAction;
import org.netbeans.spi.editor.completion.xhtml.api.CompletionItemData;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;

/**
 * <p>Defines an XHTML attribute completion item.</p>
 *
 * <p>This class is a generic completion item : it's useful for most attribute
 * completion use cases.</p>
 *
 * @author oschmitt
 */
public class AttributeCompletionItem implements CompletionItem {

    private static ImageIcon ICON;
    private final AttributeInCompletion attributeInCompletion;
    private final CompletionItemData completionItemData;

    public AttributeCompletionItem(Map annotationConfMap, AttributeInCompletion attributeInCompletion, CompletionItemData completionItemData) {
        this.completionItemData = completionItemData;
        this.attributeInCompletion = attributeInCompletion;
        ICON = new ImageIcon(ImageUtilities.loadImage(annotationConfMap.get("icon").toString()));
    }

    @Override
    public void defaultAction(JTextComponent jtc) {
        try {
            StyledDocument styledDocument = (StyledDocument) jtc.getDocument();
            int position = this.attributeInCompletion.getLineOffset() + attributeInCompletion.getStart();
            int length = attributeInCompletion.getValue().length();
            final CompleteAction completeAction = this.completionItemData.getCompleteAction();
            if (completeAction == null) {
                DefaultCompleteAction defaultCompleteAction = new DefaultCompleteAction();
                defaultCompleteAction.perform(completionItemData, styledDocument, position, length);
            } else {
                completeAction.perform(completionItemData, styledDocument, position, length);
            }
            // Close box completion
            Completion.get().hideAll();
        } catch (BadLocationException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    @Override
    public void processKeyEvent(KeyEvent ke) {
    }

    @Override
    public int getPreferredWidth(Graphics graphics, Font font) {
        return CompletionUtilities.getPreferredWidth(getText(), null, graphics, font);
    }

    @Override
    public void render(Graphics graphics, Font defaultFont, Color defaultColor,
            Color backgroundColor, int width, int height, boolean selected) {
        CompletionUtilities.renderHtml(ICON, getText(), null, graphics, defaultFont,
                (selected ? Color.white : getColor()), width, height, selected);
    }

    @Override
    public CompletionTask createDocumentationTask() {
        final String documentation = this.completionItemData.getDocumentation();
        if (documentation == null) {
            return null;
        } else {
            return new AsyncCompletionTask(new AsyncCompletionQuery() {
                @Override
                protected void query(CompletionResultSet completionResultSet, Document document, int i) {
                    completionResultSet.setDocumentation(new AttributeCompletionItemDocumentation(documentation));
                    completionResultSet.finish();
                }
            });
        }
    }

    protected Color getColor() {
        return Color.decode("0x0000B2");
    }

    @Override
    public CompletionTask createToolTipTask() {
        return null;
    }

    @Override
    public boolean instantSubstitution(JTextComponent jtc) {
        return false;
    }

    @Override
    public int getSortPriority() {
        return 0;
    }

    @Override
    public CharSequence getSortText() {
        return getText();
    }

    @Override
    public CharSequence getInsertPrefix() {
        return getText();
    }

    private String getText() {
        return this.completionItemData.getLabel();
    }
}