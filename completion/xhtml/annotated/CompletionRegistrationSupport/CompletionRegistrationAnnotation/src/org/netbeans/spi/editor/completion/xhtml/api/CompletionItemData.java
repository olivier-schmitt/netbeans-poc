/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.spi.editor.completion.xhtml.api;

/**
 *
 * @author oschmitt
 */
public class CompletionItemData {
    
    private String label;
    private String value;
    private String documentation;

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
}
