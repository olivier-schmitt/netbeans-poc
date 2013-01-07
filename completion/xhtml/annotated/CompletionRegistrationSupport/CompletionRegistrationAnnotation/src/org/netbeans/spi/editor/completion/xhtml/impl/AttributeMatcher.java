
package org.netbeans.spi.editor.completion.xhtml.impl;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.MatchResult;
import org.netbeans.spi.editor.completion.xhtml.api.AttributeInCompletion;

/**
 * <p>Classe utilitaire pour extraire l'attribut lang.</p>
 * 
 * @author oschmitt
 */
public final class AttributeMatcher {
    
    public static String LANG_PATTERN;

    public AttributeMatcher(Map map) {
        LANG_PATTERN = String.format("(%s)=\"([a-zA-Z0-9]*)\"",map.get("attribute").toString());
    }

    public boolean containsRef(String line) {
        if (line == null) {
            return false;
        }
        Scanner scanner = new Scanner(line);
        return scanner.findInLine(LANG_PATTERN) != null;
    }
    
    
    public AttributeInCompletion getValue(String line, int caret) {

        Scanner scanner = new Scanner(line);
        while (true) {
            scanner.findInLine(LANG_PATTERN);
            try {
                MatchResult matchResult = scanner.match();
                if (matchResult != null) {
                    String attr = matchResult.group(1);
                    String value = matchResult.group(2);
                    int start = matchResult.start(2);
                    int end = matchResult.end(2);
                    if ((caret >= start)
                            && (caret <= end)) {
                        AttributeInCompletion attributeInCompletion = new AttributeInCompletion();
                        attributeInCompletion.setStart(start);
                        attributeInCompletion.setEnd(end);
                        attributeInCompletion.setValue(value);
                        return attributeInCompletion;
                    }
                }
            } catch (IllegalStateException ise) {
                return null;
            }
        }
    }
}
