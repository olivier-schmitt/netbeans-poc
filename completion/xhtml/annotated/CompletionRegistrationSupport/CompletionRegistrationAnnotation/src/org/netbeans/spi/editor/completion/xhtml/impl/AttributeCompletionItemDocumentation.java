/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.spi.editor.completion.xhtml.impl;

import java.net.URL;
import org.netbeans.spi.editor.completion.CompletionDocumentation;

/**
 *
 * @author oschmitt
 */
public class AttributeCompletionItemDocumentation implements CompletionDocumentation {

    private String text;

    public AttributeCompletionItemDocumentation(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public URL getURL() {
        return null;
    }

    @Override
    public CompletionDocumentation resolveLink(String string) {
        return null;
    }

    @Override
    public javax.swing.Action getGotoSourceAction() {
        return null;
    }

   
}
