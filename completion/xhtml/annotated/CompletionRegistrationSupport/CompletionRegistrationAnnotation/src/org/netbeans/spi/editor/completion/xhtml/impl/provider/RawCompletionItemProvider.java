/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.spi.editor.completion.xhtml.impl.provider;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.netbeans.spi.editor.completion.xhtml.api.CompletionItemProvider;
import org.netbeans.spi.editor.completion.xhtml.api.CompletionItemValue;

/**
 *
 * @author oschmitt
 */
public class RawCompletionItemProvider implements CompletionItemProvider {

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
    public List<CompletionItemValue> getCompletionItemValues(String query) {
        List<CompletionItemValue> result = new ArrayList<CompletionItemValue>();
        for (String value : values) {
            if (value.startsWith(query)) {
                result.add(new CompletionItemValue(value, value));
            }
        }
        return result;
    }
}
