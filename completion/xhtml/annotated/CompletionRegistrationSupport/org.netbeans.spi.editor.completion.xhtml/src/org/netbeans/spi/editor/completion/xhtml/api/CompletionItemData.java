
package org.netbeans.spi.editor.completion.xhtml.api;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Defines all data useful to completion item.</p>
 * 
 * @author oschmitt
 */
public class CompletionItemData {
    
    private String label;
    private String value;
    private String documentation;
    private Map<String,Object> metadatas = new HashMap<String,Object>();
    private CompleteAction completeAction;
    
    public CompletionItemData(String value) {
        this.label = value;
        this.value = value;
    }
    
    public CompletionItemData(String value, String label) {
        this.label = label;
        this.value = value;
    }
    
    public CompletionItemData(String value, String label,String documentation) {
        this.label = label;
        this.value = value;
        this.documentation = documentation;
    }
    
    public void addMetadata(String key,Object value){
        this.metadatas.put(key, value);
    }
    
    public Object getMetadata(String key){
        return this.metadatas.get(key);
    }
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public CompleteAction getCompleteAction() {
        return completeAction;
    }

    public void setCompleteAction(CompleteAction completeAction) {
        this.completeAction = completeAction;
    }
}
