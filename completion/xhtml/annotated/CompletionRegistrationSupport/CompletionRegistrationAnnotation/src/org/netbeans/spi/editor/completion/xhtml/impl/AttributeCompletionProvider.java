package org.netbeans.spi.editor.completion.xhtml.impl;

import org.netbeans.spi.editor.completion.xhtml.api.AttributeInCompletion;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionProvider;
import org.netbeans.spi.editor.completion.CompletionResultSet;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.netbeans.spi.editor.completion.support.AsyncCompletionQuery;
import org.netbeans.spi.editor.completion.support.AsyncCompletionTask;
import org.netbeans.spi.editor.completion.xhtml.api.CompletionItemProvider;
import org.netbeans.spi.editor.completion.xhtml.api.CompletionItemProvider.CompletionConfigurationException;
import org.openide.text.NbDocument;
import org.openide.util.Exceptions;

/**
 * <p> Fourni la complétion pour l'attribut lang. </p>
 *
 * <p> Les codes sont issus de la norme ISO639-1 et sont stockés dans un fichier
 * CSV. </p>
 *
 * @author oschmitt
 */
public final class AttributeCompletionProvider implements CompletionProvider {

    private final Map annotationConfMap;
    private CompletionItemProvider completionItemProvider;

    static AttributeCompletionProvider create(Map m) {
        return new AttributeCompletionProvider(m);
    }

    public AttributeCompletionProvider(Map annotationConfMap) {
        this.annotationConfMap = annotationConfMap;
        try {
            String content = annotationConfMap.get("content").toString();
            this.completionItemProvider = findItemProvider(content);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private CompletionItemProvider findItemProvider(String content) 
            throws IOException, URISyntaxException, CompletionConfigurationException {
        
        URI contentUri = new URI(content);        
        String scheme = contentUri.getScheme();
        ServiceLoader<CompletionItemProvider> completionItemProviderLoader = ServiceLoader.load(CompletionItemProvider.class);
        Iterator<CompletionItemProvider> completionItemProviderIt = completionItemProviderLoader.iterator();
        while(completionItemProviderIt.hasNext()){
            CompletionItemProvider candidateCompletionItemProvider = completionItemProviderIt.next();
            if(candidateCompletionItemProvider.accept(scheme)){
                candidateCompletionItemProvider.configure(contentUri);
                return candidateCompletionItemProvider;
            }
        }
        return null;
    }

    @Override
    public CompletionTask createTask(int queryType, JTextComponent jTextComponent) {

        int position = jTextComponent.getCaretPosition();
        String text = jTextComponent.getText();
        StyledDocument styledDocument = (StyledDocument) jTextComponent.getDocument();
        int lineNumber = NbDocument.findLineNumber(styledDocument, position);
        Element lineElement = styledDocument.getDefaultRootElement().getElement(lineNumber);
        int startOffset = lineElement.getStartOffset();
        int endOffset = lineElement.getEndOffset();
        String lineOfText = text.substring(startOffset, endOffset);
        int column = NbDocument.findLineColumn(styledDocument, position);

        AttributeMatcher attributeMatcher = new AttributeMatcher(annotationConfMap);
        if (attributeMatcher.containsRef(lineOfText)) {

            final AttributeInCompletion attributeInCompletion = attributeMatcher.getValue(lineOfText, column);
            if (attributeInCompletion == null) {
                return null;
            } else {
                attributeInCompletion.setLineOffset(startOffset);
                return new AsyncCompletionTask(new AsyncCompletionQuery() {
                    @Override
                    protected void query(CompletionResultSet completionResultSet,
                            Document document, int caretOffset) {
                        List<CompletionItem> providedItems= AttributeCompletionProvider.this.completionItemProvider.getCompletionItem(attributeInCompletion,annotationConfMap);
                        completionResultSet.addAllItems(providedItems);
                        completionResultSet.finish();
                    }
                });
            }

        } else {
            return null;
        }
    }

    @Override
    public int getAutoQueryTypes(JTextComponent jTextComponent, String string) {
        return 0;
    }
}