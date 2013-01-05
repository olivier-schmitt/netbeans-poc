/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.spi.editor.completion.xhtml.impl.provider;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.xhtml.api.CompletionItemProvider;
import org.netbeans.spi.editor.completion.xhtml.api.AttributeInCompletion;
import org.netbeans.spi.editor.completion.xhtml.api.AttributeCompletionItem;

/**
 *
 * @author oschmitt
 */
public class CSVCompletionItemProvider implements CompletionItemProvider {

    private Set<String> values = new TreeSet<String>();

    @Override
    public List<CompletionItem> getCompletionItem(AttributeInCompletion attributeInCompletion, Map annotationConfMap) {
        String query = attributeInCompletion.getValue();
        List<CompletionItem> result = new ArrayList<CompletionItem>();
        for (String value : values) {
            if (value.startsWith(query)) {
                result.add(new AttributeCompletionItem(annotationConfMap, attributeInCompletion, value));
            }
        }
        return result;
    }

    @Override
    public boolean accept(String scheme) {
        return "csv".equals(scheme);
    }

    @Override
    public void configure(URI uri) throws CompletionConfigurationException {

        String path = uri.getSchemeSpecificPart();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(path);
            if (inputStream != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    values.add(line);
                }
            }
        } catch (Exception e) {
            throw new CompletionConfigurationException(e);
        }
    }
}
