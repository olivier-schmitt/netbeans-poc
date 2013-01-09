/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jee.architect.cookbook.netbeans.iso6391;

import java.util.ArrayList;
import java.util.List;
import org.netbeans.spi.editor.completion.xhtml.api.CompletionItemData;
import org.netbeans.spi.editor.completion.xhtml.api.CompletionItemDataProvider;

/**
 *
 * @author oschmitt
 */
public class Bla2CompletionItemProvider implements CompletionItemDataProvider {

    @Override
    public List<CompletionItemData> getDatas(String query) {
        List<CompletionItemData> result = new ArrayList<CompletionItemData>();
        result.add(new CompletionItemData(query,query));
        return result;
    }
}
