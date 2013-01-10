/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jee.architect.cookbook.netbeans.iso6391;

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
public class ISO6391CompletionItemService implements CompletionItemService {

    private Map<String, String> labelAndValues = new HashMap<String, String>();
    final static ISO6391CompleteAction ISO6391_ACTION = new ISO6391CompleteAction();

    @Override
    public List<CompletionItemData> getDatas(String query) {

        List<CompletionItemData> result = new ArrayList<CompletionItemData>();
        
        for (String value : this.labelAndValues.keySet()) {
        
            if (value.startsWith(query)) {
                String label = this.labelAndValues.get(value);

                CompletionItemData completionItemData = new CompletionItemData(value,
                        label,
                        String.format("Language code for %s is %s",
                        label,
                        value));
                completionItemData.setCompleteAction(ISO6391_ACTION);

                result.add(completionItemData);
            }
        }
        return result;
    }

    @Override
    public boolean accept(String scheme) {
        return ("iso6391").equals(scheme);
    }

    @Override
    public void configure(URI uri) throws CompletionConfigurationException {
        try {

            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("jee/architect/cookbook/netbeans/iso6391/ISO6391.csv");
            if (inputStream != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.contains(";")) {
                        String[] valueAndLabel = line.split(";");
                        String value = valueAndLabel[0];
                        String label = valueAndLabel[1];
                        labelAndValues.put(value, label);
                    }
                }
            }

        } catch (Exception e) {
            throw new CompletionConfigurationException(e);
        }
    }
}
