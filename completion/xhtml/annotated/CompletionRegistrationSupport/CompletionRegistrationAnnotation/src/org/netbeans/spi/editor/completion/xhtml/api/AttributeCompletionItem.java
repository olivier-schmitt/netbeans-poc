package org.netbeans.spi.editor.completion.xhtml.api;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.netbeans.api.editor.completion.Completion;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.netbeans.spi.editor.completion.support.CompletionUtilities;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;

/**
 * <p> Réprésente un item de complétion ISO369-1 : label, icône, ... ainsi que
 * l'action à exécuter lorsque l'utilisateur choisi l'item. </p>
 *
 * @author oschmitt
 */
public class AttributeCompletionItem implements CompletionItem {

    private static ImageIcon ICON;
    private String text;
    private AttributeInCompletion attribute;

    public AttributeCompletionItem(Map annotationConfMap, AttributeInCompletion attribute, String text) {
        this.text = text;
        this.attribute = attribute;
        ICON = new ImageIcon(ImageUtilities.loadImage(annotationConfMap.get("iconBase").toString()));
    }

    @Override
    public void defaultAction(JTextComponent jtc) {
        try {
            StyledDocument doc = (StyledDocument) jtc.getDocument();
            int start = this.attribute.getLineOffset() + attribute.getStart();
            doc.remove(start, attribute.getValue().length());
            doc.insertString(start, getText().substring(0, 2), null);
            // Ferme la boite de completion
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
        return null;
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
        return this.text;
    }
}
