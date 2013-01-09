/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.spi.editor.completion.xhtml.impl.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.netbeans.spi.editor.completion.xhtml.api.CompletionItemData;
import org.netbeans.spi.editor.completion.xhtml.api.CompletionItemService;

/**
 *
 * @author oschmitt
 */
public class RawCompletionItemService implements CompletionItemService {

    private Set<String> values = new TreeSet<String>();


    @Override
    public boolean accept(String scheme) {
        return "raw".equals(scheme);
    }

    @Override
    public void configure(URI uri) throws CompletionConfigurationException {
        String rawData = uri.getSchemeSpecificPart();
        values.addAll(Arrays.asList(rawData.split(",")));
    }

    @Override
    public List<CompletionItemData> getDatas(String query) {
        List<CompletionItemData> result = new ArrayList<CompletionItemData>();
        for (String value : values) {
            if (value.startsWith(query)) {
                result.add(new CompletionItemData(value));
            }
        }
        return result;
    }
}
