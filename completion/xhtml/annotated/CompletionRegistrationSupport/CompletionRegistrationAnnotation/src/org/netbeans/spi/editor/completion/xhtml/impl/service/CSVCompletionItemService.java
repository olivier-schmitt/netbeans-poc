/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.spi.editor.completion.xhtml.impl.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.netbeans.spi.editor.completion.xhtml.api.CompletionItemData;
import org.netbeans.spi.editor.completion.xhtml.api.CompletionItemService;

/**
 *
 * @author oschmitt
 */
public class CSVCompletionItemService implements CompletionItemService {

    private Map<String, String> labelAndValues = new HashMap<String, String>();

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
                    if (line.contains(";")) {
                        String[] valueAndLabel = line.split(";");
                        String value = valueAndLabel[0];
                        String label = valueAndLabel[1];
                        labelAndValues.put(value, label);
                    } else {
                        labelAndValues.put(line, line);
                    }
                }
            }

        } catch (Exception e) {
            throw new CompletionConfigurationException(e);
        }
    }

    @Override
    public List<CompletionItemData> getDatas(String query) {
        List<CompletionItemData> result = new ArrayList<CompletionItemData>();
        for (String value : this.labelAndValues.keySet()) {
            if (value.startsWith(query)) {
                String label = this.labelAndValues.get(value);
                result.add(new CompletionItemData(value, label));
            }
        }
        return result;
    }
}
