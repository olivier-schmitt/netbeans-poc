package org.netbeans.spi.editor.completion.xhtml.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.netbeans.spi.editor.completion.CompletionProvider;
import org.netbeans.spi.editor.completion.CompletionResultSet;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.netbeans.spi.editor.completion.support.AsyncCompletionQuery;
import org.netbeans.spi.editor.completion.support.AsyncCompletionTask;
import org.netbeans.spi.editor.completion.xhtml.api.CompletionItemData;
import org.netbeans.spi.editor.completion.xhtml.api.CompletionItemService;
import org.netbeans.spi.editor.completion.xhtml.api.CompletionItemService.CompletionConfigurationException;
import org.openide.text.NbDocument;
import org.openide.util.Exceptions;

/**
 *
 * @author oschmitt
 */
public final class AttributeCompletionProvider implements CompletionProvider {

    private final Map annotationConfMap;
    private CompletionItemService completionItemService;

    static AttributeCompletionProvider create(Map m) {
        return new AttributeCompletionProvider(m);
    }

    public AttributeCompletionProvider(Map annotationConfMap) {
        this.annotationConfMap = annotationConfMap;
        try {
            String content = annotationConfMap.get("content").toString();
            this.completionItemService = findCompletionItemService(content);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private CompletionItemService findCompletionItemService(String content)
            throws IOException, URISyntaxException, CompletionConfigurationException {

        URI contentUri = new URI(content);
        String scheme = contentUri.getScheme();
        ServiceLoader<CompletionItemService> completionItemServiceLoader = ServiceLoader.load(CompletionItemService.class);
        Iterator<CompletionItemService> completionItemServiceIt = completionItemServiceLoader.iterator();
        while (completionItemServiceIt.hasNext()) {
            CompletionItemService candidateCompletionItemService = completionItemServiceIt.next();
            if (candidateCompletionItemService.accept(scheme)) {
                candidateCompletionItemService.configure(contentUri);
                return candidateCompletionItemService;
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
                        
                        String query = attributeInCompletion.getValue();
                        List<CompletionItemData> providedItemsValues = AttributeCompletionProvider.this.completionItemService.getDatas(query);
                        for (CompletionItemData completionItemData : providedItemsValues) {
                            AttributeCompletionItem attributeCompletionItem = new AttributeCompletionItem(annotationConfMap, attributeInCompletion, completionItemData);
                            completionResultSet.addItem(attributeCompletionItem);
                        }
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