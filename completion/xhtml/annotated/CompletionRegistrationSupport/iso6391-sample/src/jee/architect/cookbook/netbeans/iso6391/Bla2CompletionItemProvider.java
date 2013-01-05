/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jee.architect.cookbook.netbeans.iso6391;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.xhtml.api.AttributeInCompletion;
import org.netbeans.spi.editor.completion.xhtml.api.AttributeCompletionItem;

/**
 *
 * @author oschmitt
 */
public class Bla2CompletionItemProvider {

    public List<CompletionItem> getCompletionItem(AttributeInCompletion attributeInCompletion, Map annotationConfMap) {
        String query = attributeInCompletion.getValue();
        List<CompletionItem> result = new ArrayList<CompletionItem>();
        result.add(new AttributeCompletionItem(annotationConfMap, attributeInCompletion, query));
        return result;
    }
}
